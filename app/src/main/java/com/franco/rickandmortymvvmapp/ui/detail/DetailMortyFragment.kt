package com.franco.rickandmortymvvmapp.ui.detail
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.databinding.DetailMortyFragmentBinding
import com.franco.rickandmortymvvmapp.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMortyFragment : Fragment(R.layout.detail_morty_fragment) {

    private  val detailMortyViewModel:DetailMortyViewModel by viewModels()
    var nameCharacter: String? = null
    var imageCharacter: String? = null
    var speciesCharacter: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameCharacter = arguments?.getString("characterName")
        speciesCharacter = arguments?.getString("species")
        imageCharacter = arguments?.getString("image")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailMortyFragmentBinding.inflate(layoutInflater)

        with(binding) {
            lifecycleOwner = this@DetailMortyFragment
            detailmodel = detailMortyViewModel

            collapsingToolbar.apply {
                this.setCollapsedTitleTextColor(Color.WHITE)
                setExpandedTitleColor(Color.WHITE)
                title = nameCharacter
            }
            characterName.text = "$nameCharacter"
            textView.text = "$speciesCharacter"
            image.loadUrl("$imageCharacter")

        }
        return binding.root
    }

}