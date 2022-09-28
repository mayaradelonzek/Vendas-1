package br.com.dionataferraz.vendas.schedulers

import android.os.AsyncTask
import br.com.dionataferraz.vendas.HomeActivity

open class MJobExecuter: AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg params: Void?): String {
        HomeActivity.countSeconds()
        return "Performing in Background ${HomeActivity.counter} time"
    }

}