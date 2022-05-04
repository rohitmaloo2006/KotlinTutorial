package com.rm.flowapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    companion object {
        const val TAG = "CoroutinesActivityTAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        //  launchSuspendingFunction()
        /* GlobalScope.launch {
             useWithContext()
         }*/
        useRunBlocking()

    }

    private fun testCoroutineScope() {
        // ViewmodelScope : All the coroutine will be destroyed when viewmodel is cleared
        // lifecycleScope : all the coroutine will be destroyed when activity/fragment destroyed
        CoroutineScope(Dispatchers.IO).launch {
            // We have to specify the dispatcher
            Log.d(TAG, "CoroutineScope: ${Thread.currentThread().name} ")
        }
        GlobalScope.launch() {
            // Default in worker  otherwise in given
            Log.d(TAG, "Simple launch: ${Thread.currentThread().name} ")
        }

        MainScope().launch(Dispatchers.Default) {
            // Default in Main otherwise in given dispatcher
            Log.d(TAG, "Main Activity scope->MainScope: ${Thread.currentThread().name} ")
        }

        lifecycleScope.launch(Dispatchers.IO) {
            // Default is main othewise given dispatcher
            Log.d(TAG, "lifecycleScope: ${Thread.currentThread().name} ")
        }
    }

    private fun launchSuspendingFunction() {
        // Inside launch every statement run sequentially
        CoroutineScope(Dispatchers.Main).launch {
            task1()
        }
        CoroutineScope(Dispatchers.Main).launch {
            task2()
        }

        /* CoroutineScope(Dispatchers.Main).launch {
             printFollowerForLaunch()
         }*/

        CoroutineScope(Dispatchers.Main).launch {
            printFollowerForAsync()
        }


    }

    private suspend fun task1() {
        Log.d(TAG, "task1: started")
        delay(3000)
        Log.d(TAG, "task1: Ended")
    }

    private suspend fun task2() {
        Log.d(TAG, "task2: started")
        delay(2000)
        Log.d(TAG, "task2: Ended")
    }

    // Fire and forgot
    private suspend fun printFollowerForLaunch() {
        var followerCount = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            followerCount = getFollower()
        }
        // job.join()
        Log.d(TAG, "followerCount: $followerCount")

    }

    // Need result/output from coroutine
    private suspend fun printFollowerForAsync() {
        val followerCount = CoroutineScope(Dispatchers.IO).async {
            getFollower()
        }
        // job.join()
        Log.d(TAG, "followerCount: ${followerCount.await()}")
    }

    private suspend fun getFollower(): Int {
        Log.d(TAG, "getFollower: started")
        delay(2000)
        return 56
    }

    private suspend fun useWithContext() {
        // Use for switch the thread and as its suspend function so statement after withcontext launch after completion of withcontext
        Log.d(TAG, "useWithContext started")
        withContext(Dispatchers.IO) {
            delay(2000)
            Log.d(TAG, "useWithContext inside")

        }
        Log.d(TAG, "useWithContext ended")
    }

    private fun useRunBlocking() {

        // Here runBlocking block our thread until all the coroutins are not executed
        // Mainly used for Test cases
        runBlocking {
            launch {
                Log.d(TAG, "runBlocking inside")
                delay(2000)
            }
            Log.d(TAG, "runBlocking ending ")
        }

    }
}