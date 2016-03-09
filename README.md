# HTTP/S Java Checker

[![Build Status](https://travis-ci.org/wavesoftware/java-https-checker.svg?branch=master)](https://travis-ci.org/wavesoftware/java-https-checker) [![Coverage Status](https://coveralls.io/repos/wavesoftware/java-https-checker/badge.svg?branch=master&service=github)](https://coveralls.io/github/wavesoftware/java-https-checker?branch=master) [![Maven Central](https://img.shields.io/maven-central/v/pl.wavesoftware/jhttps-checker.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22pl.wavesoftware%22%20AND%20a%3A%22jhttps-checker%22)

HTTP/S Java Checker - It can check is your Java installation can perform connection with given HTTPS address

## Debian/Ubuntu

```bash
$ wget -O jhttps-checker_0.8.0_all.deb https://github.com/wavesoftware/java-https-checker/releases/download/v0.8.0/jhttps-checker_0.8.0_all.deb
$ sudo dpkg -i jhttps-checker_0.8.0_all.deb || sudo apt-get install -f
$ jhttps-checker --help
usage: jhttps-checker [-h] [-q] [-r MAX_REDIRECTS] address

It can check is your Java installation can perform connection with given HTTPS address

positional arguments:
  address                Address to be checked

optional arguments:
  -h, --help             show this help message and exit
  -q, --quiet            Do not display anything, only retcodes (default: false)
  -r MAX_REDIRECTS, --max_redirects MAX_REDIRECTS
                         Number of redirects to perform at maximum (default: 10)
```

## Maven

```xml
<dependency>
    <groupId>pl.wavesoftware</groupId>
    <artifactId>jhttps-checker</artifactId>
    <version>0.8.0</version>
</dependency>
```
