# LoginBack

It demonstrates a more robust way to implement ` user login` based on  
`ViewModel` of [Jetpack](https://developer.android.google.cn/jetpack).


> Android 10 (API level 29) and higher place restrictions on when apps  
> can start activities when the app is running in the background. These  
> restrictions help minimize interruptions for the user and keep the user  
> more in control of what's shown on their screen.

[It has a limit](https://developer.android.google.cn/guide/components/activities/background-starts)  about starting activities from the background on Android  
10+ for a better UX.

The `ViewModel` separates the login status and UI navigation logic  
clearly: it exposed the specific `loginResult` observed by the `View`  
and the call will be triggered only when `View` is active (started/resumed).

Comparing with handling login `event` in the `View` and managing `subscribers`  
explicitly, it does save us a lot of efforts.

