package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.NoctuaInternal
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.darwin.NSObjectProtocol

/**
 * Observes iOS application lifecycle notifications and forwards them
 * to [NoctuaInternal.onInternalNoctuaApplicationPause] so that session
 * tracking (heartbeat, pause, resume) works automatically on iOS.
 *
 * Without this observer, iOS developers would need to manually call
 * `onInternalNoctuaApplicationPause` from their AppDelegate/SceneDelegate.
 */
internal class IOSLifecycleObserver {

    private var backgroundObserver: NSObjectProtocol? = null
    private var foregroundObserver: NSObjectProtocol? = null

    fun startObserving() {
        val center = NSNotificationCenter.defaultCenter

        backgroundObserver = center.addObserverForName(
            name = UIApplicationDidEnterBackgroundNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue
        ) { _ ->
            NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = true)
        }

        foregroundObserver = center.addObserverForName(
            name = UIApplicationDidBecomeActiveNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue
        ) { _ ->
            NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = false)
        }
    }

    fun stopObserving() {
        val center = NSNotificationCenter.defaultCenter
        backgroundObserver?.let { center.removeObserver(it) }
        foregroundObserver?.let { center.removeObserver(it) }
        backgroundObserver = null
        foregroundObserver = null
    }
}
