package com.dicoding.submission.capstone.detail

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.submission.capstone.FrontActivity
import com.dicoding.submission.capstone.R
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.data.datakota.Data
import com.dicoding.submission.capstone.databinding.ActivityDetailPredictBinding
import com.dicoding.submission.capstone.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import com.dicoding.submission.capstone.preditc.predictViewmodel as predictViewmodel1

class DetailPredictActivity : AppCompatActivity() {
    private lateinit var hargaTextView: TextView
    private lateinit var hargaTextView1: TextView
    private lateinit var kotaId: String
    private lateinit var kotaNameTextView: TextView
    private lateinit var photoImageView: ImageView
    private lateinit var binding: ActivityDetailPredictBinding

    private val viewModel: predictViewmodel1 by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPredictBinding.inflate(layoutInflater)

        supportActionBar?.title = "Detail Prediksi"
        setContentView(binding.root)

        hargaTextView = binding.textviwq
        hargaTextView1 = binding.tunaTextView1
        kotaId = intent.getStringExtra("kotaId").toString()
        kotaNameTextView = binding.titleTextView
        photoImageView = binding.photoImageView
        viewModel.session.observe(this) { session ->
            if (!session) {
                val intent = Intent(this, FrontActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val kota = Data.kota.find { it.id == kotaId }
        kotaNameTextView.text = kota?.name
        val drawableId = resources.getIdentifier(kota?.photo.toString(), "drawable", packageName)
        loadKotaImage(drawableId)
        observeData()


    }

    private fun observeData() {
        val text = binding.textviwq
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, Calendar.MAY)
        calendar.set(Calendar.DAY_OF_MONTH, 24)

        viewModel.listStoryByToken.observe(this) { result ->

            when (result) {
                is Result.Loading -> {
                    // Show loading state
                }
                is Result.Success -> {
                    when (kotaId) {
                        "1" -> {
                            val hargaList = result.data.first().data[0].map { it[0] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                        }
                        "2" -> {
                            val hargaList = result.data.first().data[0].map { it[1] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[14] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "3" -> {
                            val hargaList = result.data.first().data[0].map { it[2] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[15] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "4" -> {
                            val hargaList = result.data.first().data[0].map { it[3] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[16] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "5" -> {
                            val hargaList = result.data.first().data[0].map { it[4] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[17] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "6" -> {
                            val hargaList = result.data.first().data[0].map { it[5] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[18] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "7" -> {
                            val hargaList = result.data.first().data[0].map { it[6] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[19] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "8" -> {
                            val hargaList = result.data.first().data[0].map { it[7] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[20] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "9" -> {
                            val hargaList = result.data.first().data[0].map { it[8] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[21] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "10" -> {
                            val hargaList = result.data.first().data[0].map { it[9] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[22] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "11" -> {
                            val hargaList = result.data.first().data[0].map { it[10] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[23] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                        "12" -> {
                            val hargaList = result.data.first().data[0].map { it[11] as Double }
                            for ((index, hargaIkan) in hargaList.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView.append(" $date:   $hargaIkan\n")
                            }
                            val hargaList1 = result.data.first().data[0].map { it[24] as Double }
                            for ((index, hargaIkan) in hargaList1.withIndex().take(5)) {
                                calendar.set(Calendar.DAY_OF_MONTH, 24 + index)
                                val date = dateFormat.format(calendar.time)
                                hargaTextView1.append("$date:   $hargaIkan\n")
                            }
                        }
                    }

                }
                is Result.Error -> {
                    // Show error state
                }
            }
        }
    }

    private fun loadKotaImage(drawableId: Int) {
        Glide.with(this)
            .load(drawableId)
            .into(photoImageView)
    }
}
