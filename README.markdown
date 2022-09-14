Ostrolab InsecureApp Readme
==========

This is a vulnerable Android application which contains a number of vulnerable components 
and the main purpose is to identify the ability of the static and dynamic analysis to identify them

The list of vulnerabilities;


* AESCipher :
> The algorithm used to encrypt the data is vulnerable. If AES is used without specifying the mode, the default mode is the insecure ECB mode. 
[link](https://docs.ostorlab.co/kb/CRYPTO_INSECURE_CIPHER_MODE/#description)

* ClearTextTraffic : 
>Mobile Applications must use Transport Layer Security (TLS) to provide encryption at the transport layer and ensure the confidentiality and integrity of data in transit.This application does not use SSL/TLS and is vulnerable to traffic interception and modification.
[link](https://docs.ostorlab.co/kb/TLS_HTTP/)

* CommandExec : 
> Command injection is an attack in which the goal is execution of arbitrary commands on the Android operating system via a vulnerable application. Command injection attacks are possible when an application passes unsafe user supplied data (forms, cookies, HTTP headers etc.) to the system shell. In this attack, the attacker-supplied operating system commands are usually executed with the privileges of the vulnerable application. Command injection attacks are possible largely due to insufficient input validation.
[link](https://docs.ostorlab.co/kb/APK_INSECURE_EXEC_CMD/)

* DexClassLoaderCall : 
> The applications loads jar/apk stored in an insecure location. This load process can be hijacked, allowing access to private data and unexpected arbitrary code execution by malicious applications
  [link](https://docs.ostorlab.co/kb/APK_INSECURE_PATH_CLASS_LOADER/)

* ECBModeCipher : 
> insecure ECB mode.
  [link](https://docs.ostorlab.co/kb/CRYPTO_INSECURE_CIPHER_MODE/#description)

* HashCall :
  
* InsecureCommands :
> Insecure storage of sensitive information by setting insecure permissions or storing data without encryption might expose this information to an attacker.
  [link](https://docs.ostorlab.co/kb/DANGEROUS_API_EXTERNAL_STORAGE/)

* InsecureFilePermissions : 
> The application handles files using insecure permissions (world-readable or world-writable) or is targeting external memory devices like SD card with weak permissions.
  [link](https://docs.ostorlab.co/kb/INSECURE_PERMISSION_FILESYSTEM/)

* InsecureRandom : 
> Random number generator is seeded using constant value which results in the generation of predictable numbers.
  [link](https://docs.ostorlab.co/kb/INSECURE_RANDOM_SEED/)

* InsecureSharedPreferences : 
> Setting Shared Preferences with insecure permissions either world readable or world writable may expose sensitive information stored in shared preferences to arbitrary read or write by a malicious attacker.
  [link](https://docs.ostorlab.co/kb/INSECURE_PERMISSION_SHARED_PREFERENCES/)

* IntentCall :
  [link]

* MemoryCorruption : 
> memory corruption vulnerabilities can lead to denial of service, information leak, arbitrary read and write or remote code execution.
  [link](https://docs.ostorlab.co/kb/DANGEROUS_MEMORY_CORRUPTION/)

* MobileOnlyDownloadManager : 
> The download manager is a system service that handles long-running HTTP downloads. The application should not force using only the mobile network connection
  [link](https://docs.ostorlab.co/kb/APK_INSECURE_DOWNLOAD_MANAGER/)

* PathClassLoaderCall : 
> The applications loads jar/apk stored in an insecure location. This load process can be hijacked, allowing access to private data and unexpected arbitrary code execution by malicious applications
  [link](https://docs.ostorlab.co/kb/APK_INSECURE_PATH_CLASS_LOADER/)

* SQLiteDatabaseCall : 
> Improper SQL query construction could lead to SQL injection. An SQL injection attack consists of injecting of an SQL query via the input data from the client to the application
  [link](https://docs.ostorlab.co/kb/DANGEROUS_API_SQL/)

* StaticIV: 
> Use of a non-random initialization vector makes the application vulnerable to dictionary attacks.
  [link](https://docs.ostorlab.co/kb/CRYPTO_INSECURE_IV/)

* TLSTraffic : 
> It was identified that the endpoint supports a combination of cipher suites and Secure Sockets Layer / Transport Layer Security (SSL/TLS) protocols that suffer from known cryptographic weaknesses.
  [link](https://docs.ostorlab.co/kb/TLS_INSECURE_CIPHER/)

* WebviewInsecureSettings : 
> Webview debugging uses the Chrome Debug Protocol and is exposed using an abstract named unix socket.Abstract sockets do not use file system permissions to enforce access and are therefore accessible to all applications on the device.
  [link](https://docs.ostorlab.co/kb/DANGEROUS_API_WEBVIEW_REMOTE_DEBUGGING_ENABLED/)

* ParcelableMemoryCorruption :
> Controlling the object and json payload of the parcelable can be used to cause memory corruption with native android library such as [VirtualRefBasePtr](https://github.com/aosp-mirror/platform_frameworks_base/blob/6bebb8418ceecf44d2af40033870f3aabacfe36e/core/java/com/android/internal/util/VirtualRefBasePtr.java),

*  SerializableMemoryCorruption :
> Controlling the object and json payload of the serializable can be used to cause memory corruption with native android library such as [VirtualRefBasePtr](https://github.com/aosp-mirror/platform_frameworks_base/blob/6bebb8418ceecf44d2af40033870f3aabacfe36e/core/java/com/android/internal/util/VirtualRefBasePtr.java),

* Path Traversal Vulnerability
>  Note that calling getLastPathSegment on the Uri parameter is not safe. A malicious app can supply an encoded Uri path like %2F..%2F..path%2Fto%2Fsecret.txt so the result of getLastPathSegment will be /../../path/to/secret.txt.[link](https://support.google.com/faqs/answer/7496913?hl=en)

* Implicit PendingIntent Vulnerability
> PendingIntents are Intents delegated to another app to be delivered at some future time. Creating an implicit intent wrapped under a PendingIntent is a security vulnerability that might lead to denial-of-service, private data theft, and privilege escalation.[link](https://support.google.com/faqs/answer/10437428?hl=en) 