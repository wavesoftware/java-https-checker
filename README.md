# HTTP/S Java Checker

[![Build Status](https://travis-ci.org/wavesoftware/java-https-checker.svg?branch=master)](https://travis-ci.org/wavesoftware/java-https-checker) [![Coverage Status](https://coveralls.io/repos/wavesoftware/java-https-checker/badge.svg?branch=master&service=github)](https://coveralls.io/github/wavesoftware/java-https-checker?branch=master) [![Maven Central](https://img.shields.io/maven-central/v/pl.wavesoftware/jhttps-checker.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22pl.wavesoftware%22%20AND%20a%3A%22jhttps-checker%22)

HTTP/S Java Checker - It can check is your Java installation can perform connection with given HTTPS address

## Installation on Debian/Ubuntu

```bash
$ wget -O jhttps-checker_0.8.0_all.deb https://github.com/wavesoftware/java-https-checker/releases/download/v0.8.0/jhttps-checker_0.8.0_all.deb
$ sudo dpkg -i jhttps-checker_0.8.0_all.deb || sudo apt-get install -f
```

## Usage

```bash
$ jhttps-checker https://sonar.wavesoftware.pl/
Request URL: https://sonar.wavesoftware.pl/
------
Response OK?: true
------
HTTP/1.1 200 OK
X-Runtime: 449
ETag: "d3d61c144eacacfd6a426b23200868b3"
Content-Length: 20125
X-XSS-Protection: 1; mode=block
Connection: keep-alive
Server: nginx/1.4.6 (Ubuntu)
X-Content-Type-Options: nosniff
Cache-Control: private, max-age=0, must-revalidate
X-Frame-Options: SAMEORIGIN
Date: Wed, 09 Mar 2016 20:23:14 GMT
Vary: Accept-Encoding
Content-Type: text/html;charset=utf-8

$ jhttps-checker https://api.sandbox.paypal.com/
Request URL: https://api.sandbox.paypal.com/
javax.net.ssl.SSLHandshake: Received fatal alert: handshake_failure

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

## as Maven dependency

```xml
<dependency>
    <groupId>pl.wavesoftware</groupId>
    <artifactId>jhttps-checker</artifactId>
    <version>0.8.0</version>
</dependency>
```
