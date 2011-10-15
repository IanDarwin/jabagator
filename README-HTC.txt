Tried installing the tablet API from the file:///... using AVD Manager

Downloading HTC SDK for Tablets by HTC, Android API 12, revision 1
Installing HTC SDK for Tablets by HTC, Android API 12, revision 1
Unzip failed: C:\Program Files (x86)\Android\android-sdk\add-ons\addon_htc_sdk_for_tablets_htc_12-1\docs\reference\CommonControl\assets\customizations.js (Access is denied)
Downloading HTC SDK for Phones by HTC, Android API 10, revision 1
Installing HTC SDK for Phones by HTC, Android API 10, revision 1
Unzip failed: C:\Program Files (x86)\Android\android-sdk\add-ons\addon_htc_sdk_for_phones_htc_10-1\addon_htc_phone_ext_1.0\docs\reference\CommonControl\assets\._doclava-developer-docs.js (Access is denied)

The relevant directory does exist and is "owned" by me so it should be writable:

C:\Users\Ian\HTCOpenSense>dir  "C:\Program Files (x86)\Android\android-sdk\add-o
ns"
 Volume in drive C is OS
 Volume Serial Number is E673-0E2B

 Directory of C:\Program Files (x86)\Android\android-sdk\add-ons

13/10/2011  05:35 AM    <DIR>          .
13/10/2011  05:35 AM    <DIR>          ..
               0 File(s)              0 bytes
               2 Dir(s)  30,619,987,968 bytes free

C:\Users\Ian\HTCOpenSense>

