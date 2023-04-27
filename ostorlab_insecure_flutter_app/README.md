# Ostorlab Insecure app - vulnerable flutter module
==========

This is a vulnerable Flutter module which contains a number of vulnerabilities
and the main purpose is to identify the ability of the static and dynamic analysis to identify them

The list of vulnerabilities;

* ECBModeCipher :
> insecure ECB mode.
[link](https://docs.ostorlab.co/kb/CRYPTO_INSECURE_CIPHER_MODE/#description)

* ClearTextTraffic :
>Mobile Applications must use Transport Layer Security (TLS) to provide encryption at the transport layer and ensure the confidentiality and integrity of data in transit.This application does not use SSL/TLS and is vulnerable to traffic interception and modification.
[link](https://docs.ostorlab.co/kb/TLS_HTTP/)

* TLSTraffic :
> It was identified that the endpoint supports a combination of cipher suites and Secure Sockets Layer / Transport Layer Security (SSL/TLS) protocols that suffer from known cryptographic weaknesses.
[link](https://docs.ostorlab.co/kb/TLS_INSECURE_CIPHER/)

* StaticIV:
> Use of a non-random initialization vector makes the application vulnerable to dictionary attacks.
[link](https://docs.ostorlab.co/kb/CRYPTO_INSECURE_IV/)

* HardcodedCredsInUrl:
> [TODO]