package uz.pdp.newcurrency.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.pdp.newcurrency.AboutFragment

class CategoryAdapter(fragment: Fragment, val titleList: ArrayList<String>) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = titleList.size

    override fun createFragment(position: Int): Fragment {
        return AboutFragment.newInstance(titleList[position], position)
    }
}