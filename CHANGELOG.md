# Change Log for Amazon Corretto 23

The following sections describe the changes for each release of Amazon Corretto 23.

## Corretto version: 23.0.1.8.1
Release Date: October 15, 2024

**Target Platforms <sup>1</sup>**

+ RPM-based Linux using glibc 2.17 or later, x86_64
+ Debian-based Linux using glibc 2.17 or later, x86_64
+ RPM-based Linux using glibc 2.17 or later, aarch64
+ Debian-based Linux using glibc 2.17 or later, aarch64
+ Alpine-based Linux, x86_64
+ Alpine-based Linux, aarch64
+ Windows 10 or later, x86_64
+ macos 12.0 and later, x86_64
+ macos 12.0 and later, aarch64


**1.** This is the platform targeted by the build. See [Using Amazon Corretto](https://aws.amazon.com/corretto/faqs/#Using_Amazon_Corretto)
in the Amazon Corretto FAQ for supported platforms

The following issues are addressed in 23.0.1.8.1:

| Issue Name                                                        | Platform | Description                                                                        | Link                                                                   |
|-------------------------------------------------------------------|----------|------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| Import jdk-23.0.1+8                                               | All      | Updates Corretto baseline to OpenJDK 23.0.1+8                                      | [jdk-23.0.1+8](https://github.com/openjdk/jdk23u/releases/tag/jdk-23.0.1+8) |

The following CVEs are addressed in 23.0.1.8.1:

| CVE            | CVSS | Component                       |
|----------------|------|---------------------------------|
| CVE-2024-21235 | 4.8  | hotspot/compiler                |
| CVE-2024-21208 | 3.7  | core-libs/java.net              |
| CVE-2024-21210 | 3.7  | hotspot/compiler                |
| CVE-2024-21217 | 3.7  | core-libs/java.io:serialization |

## Corretto version: 23.0.0.37.1
Release Date: September 17, 2024

**Target Platforms <sup>1</sup>**

+ RPM-based Linux using glibc 2.17 or later, x86_64
+ Debian-based Linux using glibc 2.17 or later, x86_64
+ RPM-based Linux using glibc 2.17 or later, aarch64
+ Debian-based Linux using glibc 2.17 or later, aarch64
+ Alpine-based Linux, x86_64
+ Alpine-based Linux, aarch64
+ Windows 10 or later, x86_64
+ macos 12.0 and later, x86_64
+ macos 12.0 and later, aarch64


**1.** This is the platform targeted by the build. See [Using Amazon Corretto](https://aws.amazon.com/corretto/faqs/#Using_Amazon_Corretto)
in the Amazon Corretto FAQ for supported platforms

The following issues are addressed in 23.0.0.37.1:

| Issue Name                                                        | Platform | Description                                                                        | Link                                                                   |
|-------------------------------------------------------------------|----------|------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| Import jdk-23+37                                                  | All      | Updates Corretto baseline to OpenJDK 23+37                                         | [jdk-23+37](https://github.com/openjdk/jdk/releases/tag/jdk-23+37)     |
| Add debug level into alpine build                                 | Alpine   | Fixes Alpine debug builds actually being release builds                            | [corretto-23#4](https://github.com/corretto/corretto-23/pull/4)        |

## Corretto version: 23.0.0.36.1
Release Date: August 22, 2024

**Target Platforms <sup>1</sup>**

+ RPM-based Linux using glibc 2.17 or later, x86_64
+ Debian-based Linux using glibc 2.17 or later, x86_64
+ RPM-based Linux using glibc 2.17 or later, aarch64
+ Debian-based Linux using glibc 2.17 or later, aarch64
+ Alpine-based Linux, x86_64
+ Alpine-based Linux, aarch64
+ Windows 10 or later, x86_64
+ macos 12.0 and later, x86_64
+ macos 12.0 and later, aarch64


**1.** This is the platform targeted by the build. See [Using Amazon Corretto](https://aws.amazon.com/corretto/faqs/#Using_Amazon_Corretto)
in the Amazon Corretto FAQ for supported platforms

The following issues are addressed in 23.0.0.36.1:

| Issue Name                                                        | Platform | Description                                                                        | Link                                                                   |
|-------------------------------------------------------------------|----------|------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| Import jdk-23+36                                               | All      | Updates Corretto baseline to OpenJDK 23+36                                      | [jdk-23+36](https://github.com/openjdk/jdk/releases/tag/jdk-23+36) |

## Corretto version: 23.0.0.35.1
Release Date: August 12, 2024

**Target Platforms <sup>1</sup>**

+ RPM-based Linux using glibc 2.17 or later, x86_64
+ Debian-based Linux using glibc 2.17 or later, x86_64
+ RPM-based Linux using glibc 2.17 or later, aarch64
+ Debian-based Linux using glibc 2.17 or later, aarch64
+ Alpine-based Linux, x86_64
+ Alpine-based Linux, aarch64
+ Windows 10 or later, x86_64
+ macos 12.0 and later, x86_64
+ macos 12.0 and later, aarch64


**1.** This is the platform targeted by the build. See [Using Amazon Corretto](https://aws.amazon.com/corretto/faqs/#Using_Amazon_Corretto)
in the Amazon Corretto FAQ for supported platforms

The following issues are addressed in 23.0.0.35.1:

| Issue Name                                                        | Platform | Description                                                                        | Link                                                                   |
|-------------------------------------------------------------------|----------|------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| Import jdk-23+35                                               | All      | Updates Corretto baseline to OpenJDK 23+35                                      | [jdk-23+35](https://github.com/openjdk/jdk/releases/tag/jdk-23+35) |
