package xebia.ismail.e_learning

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.PowerManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import java.util.*


class NewOrderActivity : AppCompatActivity() {


    @SuppressLint("InvalidWakeLockTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_order_main)
        initView()
        wakeLock()

        val handler = Handler()
        val finishTime = 15
        handler.postDelayed({ this.finish() }, (finishTime * 1500).toLong())
    }

    fun tomarOrden(view: View) {

        val coordenada_evacuacion = String.format(Locale.getDefault(), "google.navigation:q=-12.073743,-77.163599&mode=w")
        val intentTraceRoute = Intent(Intent.ACTION_VIEW)
        intentTraceRoute.data = Uri.parse(coordenada_evacuacion)
        intentTraceRoute.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
        startActivity(intentTraceRoute);
    }

    private fun initView() {
        findViewById<TextView>(R.id.texto)
    }

    @SuppressLint("InvalidWakeLockTag")
    private fun wakeLock() {
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        val mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE, "AlarmActivity")
        mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED + WindowManager.LayoutParams.FLAG_FULLSCREEN or +WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        mWakeLock.release()
    }
}
