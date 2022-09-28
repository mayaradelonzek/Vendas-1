package br.com.dionataferraz.vendas.schedulers

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class MJobScheduler: JobService() {

    private lateinit var mJobExecuter: MJobExecuter

    override fun onStartJob(params: JobParameters?): Boolean {
        mJobExecuter.cancel(true)
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        mJobExecuter = object : MJobExecuter() {

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)

                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                jobFinished(params, false)
            }
        }
        return true
    }
}