package br.com.dionataferraz.vendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toobar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = ViewPagerAdapter(supportFragmentManager)
            binding.viewPager.adapter = adapter

            binding.tablayout.setupWithViewPager(binding.viewPager)
    }
}

class ViewPagerAdapter(fm: FragmentManager):
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab ${position + 1}"
    }

    override fun getItem(position: Int): Fragment {
        return ContainerFragment.newInstance("${position + 1}")
    }
}