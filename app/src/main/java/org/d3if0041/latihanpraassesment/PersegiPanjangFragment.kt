package org.d3if0041.latihanpraassesment


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.view.*
import org.d3if0041.latihanpraassesment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
private const val KEY_PANJANG = "key_panjang"
private const val KEY_LEBAR = "key_lebar"
private const val KEY_LUAS = "key_luas"
private  const val KEY_KELILING = "key_keliling"
class PersegiPanjangFragment : Fragment() {

    private lateinit var binding : FragmentPersegiPanjangBinding
    private var panjang = 0
    private var lebar = 0
    private var luas = 0
    private var keliling = 0

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_persegi_panjang,container,false)


        binding.textViewHasil.visibility = View.GONE
        binding.buttonShare.visibility = View.GONE



        binding.buttonHitung.setOnClickListener {
            if(binding.editTextPanjang.text.isEmpty() || binding.editTextPanjang.text.isEmpty()){
                Toast.makeText(context,"Data Harus Diisi",Toast.LENGTH_LONG).show()
            }
            else{
                binding.textViewHasil.visibility = View.VISIBLE
                binding.buttonShare.visibility = View.VISIBLE

                panjang = binding.editTextPanjang.text.toString().toInt()
                lebar = binding.editTextLebar.text.toString().toInt()

                luas = panjang * lebar
                keliling = 2 * (panjang + lebar)

                binding.textViewHasil.text = "Luas = $luas \n Keliling = $keliling"
            }

        }
        binding.buttonShare.setOnClickListener {
            val intent = ShareCompat.IntentBuilder.from(context as Activity)
                    .setType("plain/text")
                    .setText("Luas = $luas \n" +
                            " Keliling = $keliling")
                    .intent
                    .setAction(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_SUBJECT, "Hasil")
            startActivity(intent)
        }
        if(savedInstanceState != null){
            binding.textViewHasil.visibility = View.VISIBLE
            binding.buttonShare.visibility = View.VISIBLE

            panjang = savedInstanceState.getInt(KEY_PANJANG)
            lebar = savedInstanceState.getInt(KEY_LEBAR)
            luas = savedInstanceState.getInt(KEY_LUAS,0)
            keliling = savedInstanceState.getInt(KEY_KELILING,0)
        }
        binding.textViewHasil.text = "Luas = $luas \n Keliling = $keliling"



        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_PANJANG,panjang)
        outState.putInt(KEY_LEBAR,lebar)
        outState.putInt(KEY_LUAS,luas)
        outState.putInt(KEY_KELILING,keliling)
    }
}
