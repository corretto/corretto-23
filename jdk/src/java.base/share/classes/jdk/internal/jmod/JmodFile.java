/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.internal.jmod;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Helper class to read JMOD file
 */
public class JmodFile implements AutoCloseable {
    // jmod magic number and version number
    public static final int JMOD_MAJOR_VERSION = 0x01;
    public static final int JMOD_MINOR_VERSION = 0x00;
    public static final byte[] JMOD_MAGIC_NUMBER = {
        0x4A, 0x4D, /* JM */
        JMOD_MAJOR_VERSION, JMOD_MINOR_VERSION, /* version 1.0 */
    };

    public static void checkMagic(Path file) throws IOException {
        try (InputStream in = Files.newInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(in)) {
            // validate the header
            byte[] magic = new byte[4];
            bis.read(magic);
            if (magic[0] != JMOD_MAGIC_NUMBER[0] ||
                magic[1] != JMOD_MAGIC_NUMBER[1]) {
                throw new IOException("Invalid jmod file: " + file.toString());
            }
            if (magic[2] > JMOD_MAJOR_VERSION ||
                (magic[2] == JMOD_MAJOR_VERSION && magic[3] > JMOD_MINOR_VERSION)) {
                throw new IOException("Unsupported jmod version: " +
                    magic[2] + "." + magic[3] + " in " + file.toString());
            }
        }
    }

    /**
     * JMOD sections
     */
    public static enum Section {
        NATIVE_LIBS("native"),
        NATIVE_CMDS("bin"),
        CLASSES("classes"),
        CONFIG("conf"),
        HEADER_FILES("include"),
        MAN_PAGES("man");

        private final String jmodDir;
        private Section(String jmodDir) {
            this.jmodDir = jmodDir;
        }

        /**
         * Returns the directory name in the JMOD file corresponding to
         * this section
         */
        public String jmodDir() { return jmodDir; }

    }

    /**
     * JMOD file entry.
     *
     * Each entry corresponds to a ZipEntry whose name is:
     *   Section::jmodDir + '/' + name
     */
    public static class Entry {
        private final ZipEntry zipEntry;
        private final Section section;
        private final String name;

        private Entry(ZipEntry e) {
            String name = e.getName();
            int i = name.indexOf('/');
            if (i <= 1) {
                throw new RuntimeException("invalid jmod entry: " + name);
            }

            this.zipEntry = e;
            this.section = section(name);
            this.name = name.substring(i+1);
        }

        /**
         * Returns the section of this entry.
         */
        public Section section() {
            return section;
        }

        /**
         * Returns the name of this entry.
         */
        public String name() {
            return name;
        }

        /**
         * Returns the size of this entry.
         */
        public long size() {
            return zipEntry.getSize();
        }

        public ZipEntry zipEntry() {
            return zipEntry;
        }

        @Override
        public String toString() {
            return section.jmodDir() + "/" + name;
        }

        static Section section(String name) {
            int i = name.indexOf('/');
            String s = name.substring(0, i);
            switch (s) {
                case "native":
                    return Section.NATIVE_LIBS;
                case "bin":
                    return Section.NATIVE_CMDS;
                case "classes":
                    return Section.CLASSES;
                case "conf":
                    return Section.CONFIG;
                case "include":
                    return Section.HEADER_FILES;
                case "man":
                    return Section.MAN_PAGES;
                default:
                    throw new IllegalArgumentException("invalid section: " + s);
            }
        }
    }

    private final Path file;
    private final ZipFile zipfile;

    /**
     * Constructs a {@code JmodFile} from a given path.
     */
    public JmodFile(Path file) throws IOException {
        checkMagic(file);
        this.file = file;
        this.zipfile = new ZipFile(file.toFile());
    }

    /**
     * Opens an {@code InputStream} for reading the named entry of the given
     * section in this jmod file.
     *
     * @throws IOException if the named entry is not found, or I/O error
     *         occurs when reading it
     */
    public InputStream getInputStream(Section section, String name)
        throws IOException
    {

        String entry = section.jmodDir() + "/" + name;
        ZipEntry e = zipfile.getEntry(entry);
        if (e == null) {
            throw new IOException(name + " not found: " + file);
        }
        return zipfile.getInputStream(e);
    }

    /**
     * Returns a stream of non-directory entries in this jmod file.
     */
    public Stream<Entry> stream() {
        return zipfile.stream()
                      .filter(e -> !e.isDirectory())
                      .map(Entry::new);
    }

    @Override
    public void close() throws IOException {
        if (zipfile != null) {
            zipfile.close();
        }
    }
}