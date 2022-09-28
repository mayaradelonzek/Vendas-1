package br.com.dionataferraz.vendas

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.dionataferraz.vendas.account.AccountActivity
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase
import br.com.dionataferraz.vendas.schedulers.MJobScheduler
import br.com.dionataferraz.vendas.transaction.TransactionsActivity
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val databaseAcc: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    private val databaseUser: VendasDatabase by lazy {
        VendasDatabase.getInstance(context = App.context)
    }

    companion object {
        val JOB_ID: Int = 101
        var counter: Int = 0

        fun countSeconds() {
            counter++;
        }
    }

    private lateinit var jobScheduler: JobScheduler
    private lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        setContentView(binding.root)
        var cn: ComponentName = ComponentName(this, MJobScheduler::class.java)
        var builder: JobInfo.Builder = JobInfo.Builder(JOB_ID, cn)
        builder.setPeriodic(5000)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        builder.setPersisted(true)
        jobInfo = builder.build()
        jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        configureActionBar()
        setViewValues()

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.newAcc.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.listTransac.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }

        binding.tvAccountBalance.setOnClickListener {
            val intent  = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }

        scheduleJob()
    }

    fun scheduleJob() {
        jobScheduler.schedule(jobInfo)
        Toast.makeText(this, "Job schedule...", Toast.LENGTH_LONG).show()
    }

    fun clearJob(v: View) {
        jobScheduler.cancel(JOB_ID)
//        jobScheduler.cancelAll() //cancel all jobs
        Toast.makeText(this, "Job Cancelled...", Toast.LENGTH_LONG).show()
    }

    fun getAccValues(): AccountEntity {
        val account = databaseAcc.AccDao().getAccount()
        if (account.isEmpty()) {
            val newAcc = AccountEntity(
                id = 1,
                value = 0.0,
                date = Date()
            )
            databaseAcc.AccDao().insertAccount(newAcc)
        }
        return databaseAcc.AccDao().getAccount().get(0)
    }

    private fun setViewValues() {
       val account = getAccValues()
        val user = databaseUser.DAO().getUser().get(0)
        binding.tvAccountBalance.text = "R$ " + account.value.toString()
        binding.tvLoggedUser.text = user.name
    }

    private fun configureActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

