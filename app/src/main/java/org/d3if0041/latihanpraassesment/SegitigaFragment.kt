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
import org.d3if0041.latihanpraassesment.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
private const val KEY_ALAS = "key_alas"
private const val KEY_TINGGI = "key_tinggi"
private const val KEY_LUAS = "key_luas"
private const val KEY_KELILING = "key_alas"

class SegitigaFragment : Fragment() {

    private lateinit var binding : FragmentSegitigaBinding
    private var alas = 0
    private var tinggi = 0
    private var luas = 0
    private var keliling = 0
    private var sisiMiring = 0

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_segitiga,container,false)
        binding.textViewHasil.visibility = View.GONE
        binding.buttonShare.visibility = View.GONE

        binding.buttonHitung.setOnClickListener {
            if(binding.editTextAlas.text.isEmpty() || binding.editTextTinggi.text.isEmpty()){
                Toast.makeText(context,"Data Harus Diisi", Toast.LENGTH_LONG).show()
            }
            else{
                binding.textViewHasil.visibility = View.VISIBLE
                binding.buttonShare.visibility = View.VISIBLE

                alas = binding.editTextAlas.text.toString().toInt()
                tinggi = binding.editTextAlas.text.toString().toInt()
                luas = (alas * tinggi) / 2
                sisiMiring = Math.sqrt(((alas * alas)+ (tinggi * tinggi)).toDouble()).toInt()
                keliling = alas + tinggi + sisiMiring
                binding.textViewHasil.text = "Luas = $luas \n Keliling = $keliling"
            }
        }
        if(savedInstanceState != null){
            binding.textViewHasil.visibility = View.VISIBLE
            binding.buttonShare.visibility = View.VISIBLE

            alas = savedInstanceState.getInt(KEY_ALAS)
            tinggi = savedInstanceState.getInt(KEY_TINGGI)
            luas = savedInstanceState.getInt(KEY_LUAS)
            keliling = savedInstanceState.getInt(KEY_KELILING)
        }

        binding.textViewHasil.text = "Luas = $luas \n Keliling = $keliling"

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
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putInt(KEY_ALAS,alas)
        outState.putInt(KEY_TINGGI,tinggi)
        outState.putInt(KEY_LUAS,luas)
        outState.putInt(KEY_KELILING,keliling)
    }


}
