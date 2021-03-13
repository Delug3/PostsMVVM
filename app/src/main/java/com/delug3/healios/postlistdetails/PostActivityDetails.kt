package com.delug3.healios.postlistdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delug3.healios.databinding.ActivityDetailsPostsBinding

class PostActivityDetails : AppCompatActivity() {

    lateinit var binding: ActivityDetailsPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title: String?
        val body: String?
        //val user: String?
        //val comments data


        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
            } else {
                title = extras.getString("POST_TITLE")
                body = extras.getString("POST_BODY")


                binding.textViewDetailsTitle.text = title
                binding.textViewDetailsBody.text = body
                //binding.textViewDetailsUser.text = user
                //binding.textViewDetailsComments.text = comments

            }
        } else {
            savedInstanceState.getSerializable("POST_TITLE") as String?
            savedInstanceState.getSerializable("POST_BODY") as String?

        }
    }


}