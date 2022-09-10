package br.com.dionataferraz.vendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }

        binding.newAcc.setOnClickListener {
//            viewModel.login(null, null)
            val intent  = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

    }
}

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence {
        return "Tab ${position.plus(1)}"
    }

    override fun getItem(position: Int): Fragment {
        return ConatinerFragment.newInstance("${position.plus(1)}")
    }

}