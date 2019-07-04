package com.wizy.test.Fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.myhexaville.smartimagepicker.ImagePicker
import com.wizy.test.BaseApplication
import com.wizy.test.Util.FileUtil
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_academics_detail.*
import java.io.File


class PersonalVerificationFragment : Fragment() {
    private var userChoosenTask = ""
    private var isSelectedOption = false
    private var isSelectedCancel = 0
    private var selectOption = ""
    val firebaseStorage = FirebaseStorage.getInstance()
    var firebaseStorageRef = firebaseStorage.reference
    var imagesRef: StorageReference? = firebaseStorageRef.child("images")


    private var imagePicker: ImagePicker? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academics_detail, container, false)
    }

    // Make Fragment Instance
    companion object {
        fun newInstance(): PersonalVerificationFragment {
            return PersonalVerificationFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(BaseApplication.instance.userModel.hobbies!=null){
            textViewTitle.text=resources.getString(R.string.hobbies)
        }


        val occupationAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.spinner_gender,
            resources.getStringArray(R.array.occupation_array)
        )



        spinnerOccupation.adapter = occupationAdapter
        spinnerOccupation.setPopupBackgroundDrawable(resources.getDrawable(R.drawable.spinner_gender))


        val experienceAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.spinner_gender,
            resources.getStringArray(R.array.experience_array)
        )
        spinnerExperience.adapter = experienceAdapter
        spinnerExperience.setPopupBackgroundDrawable(resources.getDrawable(R.drawable.spinner_gender))

        val qualificationAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.spinner_gender,
            resources.getStringArray(R.array.qualification_array)
        )
        spinnerQualification.adapter = qualificationAdapter
        spinnerQualification.setPopupBackgroundDrawable(resources.getDrawable(R.drawable.spinner_gender))

        spinnerAreaOccupation.setPopupBackgroundDrawable(resources.getDrawable(R.drawable.spinner_gender))

        spinnerQualification.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, newview: View?, position: Int, id: Long) {
                if (position == 0) {
                    val qualificationGraduateAdapter = ArrayAdapter<String>(
                        activity!!,
                        R.layout.spinner_gender,
                        resources.getStringArray(R.array.qualification_graduate_array)
                    )
                    spinnerAreaOccupation.adapter = qualificationGraduateAdapter
                } else {
                    val qualificationPostAdapter = ArrayAdapter<String>(
                        activity!!,
                        R.layout.spinner_gender,
                        resources.getStringArray(R.array.qualification_post_array)
                    )
                    spinnerAreaOccupation.adapter = qualificationPostAdapter
                }
            }
        }

        imageViewPicture.setOnClickListener {
            selectOption = "avatar"
            selectImage()
        }

        textViewUpload.setOnClickListener {
            selectOption = "identity"
            selectImage()
        }

        imageViewCertificate.setOnClickListener {
            selectOption = "certificate"
            selectImage()
        }

        editTextQualification.setOnClickListener {
            val date: String = if (editTextQualification.text.toString().isEmpty()) {
                Utility.getTodayDate("dd-MM-YYYY")
            } else {
                editTextQualification.text.toString().trim()
            }
            Utility.makeCustomDatePickerDialog(
                activity!!,
                date,
                Utility.getTodayDate("dd-MM-YYYY"),
                "-",
                true,
                "past",
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val stringSelectedDate = dayOfMonth.toString() + "-" + (month + 1).toString() + "-" + year
                    Log.d("Hello",year.toString())
                    editTextQualification.setText(stringSelectedDate)
                })
        }

        buttonNext.setOnClickListener {
            when {
                BaseApplication.instance.userModel.picture == null -> Toast.makeText(activity, resources.getString(R.string.picture_select_error), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.certificate == null -> Toast.makeText(activity, resources.getString(R.string.certificate_error), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.identifyProof == null -> Toast.makeText(activity, resources.getString(R.string.identity_proof_error), Toast.LENGTH_SHORT).show()
                editTextUniversity.text.toString().trim() == "" -> Toast.makeText(activity, resources.getString(R.string.university_error_msg), Toast.LENGTH_SHORT).show()
                editTextSpecialisation.text.toString().trim() == "" -> Toast.makeText(activity, resources.getString(R.string.specialization_error), Toast.LENGTH_SHORT).show()
                editTextQualification.text.toString().trim() == "" -> Toast.makeText(activity,resources.getString(R.string.qualification_year_msg), Toast.LENGTH_SHORT).show()
                else -> {
                    BaseApplication.instance.userModel.occupation_option = spinnerOccupation.selectedItem.toString()
                    BaseApplication.instance.userModel.experience_option = spinnerExperience.selectedItem.toString()
                    BaseApplication.instance.userModel.qualification_option = spinnerQualification.selectedItem.toString()
                    BaseApplication.instance.userModel.areaOfQualification_option =
                        spinnerAreaOccupation.selectedItem.toString()
                    BaseApplication.instance.userModel.university = editTextUniversity.text.toString().trim()
                    BaseApplication.instance.userModel.specialisation = editTextSpecialisation.text.toString().trim()
                    BaseApplication.instance.userModel.qualificationYear = editTextQualification.text.toString().trim()

                    if (BaseApplication.instance.hobbiesSelectList.size > 0) {
                        Utility.addFragmentForBackStack(
                            fragmentManager!!,
                            AcademicsOverViewFragment::class.java.name,
                            AcademicsOverViewFragment.newInstance(), ""
                        )
                    } else {
                        Utility.addFragmentForBackStack(
                            fragmentManager!!,
                            BoardSelectFragment::class.java.name,
                            BoardSelectFragment.newInstance(), ""
                        )
                    }
                }
            }




        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker!!.handleActivityResult(resultCode, requestCode, data)
    }


    private fun uploadFileToServer(file: File, selectOption: String) {
        val uriUpload = Uri.fromFile(file.absoluteFile)
        if (selectOption == "identity")
            textViewUploadFileLBL.text = uriUpload.lastPathSegment
        val filesRef = imagesRef!!.child("images/" + uriUpload.lastPathSegment)

        filesRef.putFile(uriUpload).continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation filesRef.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                when (selectOption) {
                    "avatar" -> {
                        val downloadUri = task.result
                        BaseApplication.instance.userModel.picture = downloadUri.toString()
                    }
                    "certificate" -> {
                        val downloadUri = task.result
                        BaseApplication.instance.userModel.certificate = downloadUri.toString()
                    }
                    else -> {
                        val downloadUri = task.result
                        BaseApplication.instance.userModel.identifyProof = downloadUri.toString()
                    }
                }
            } else {
                Toast.makeText(activity!!, "Something went wrong!!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imagePicker!!.handlePermission(requestCode, grantResults)
    }


    private fun chooseFromGallery() {
        refreshImagePicker()
        imagePicker!!.choosePicture(false)
    }

    private fun openCamera() {
        refreshImagePicker()
        imagePicker!!.openCamera()
    }

    private fun refreshImagePicker() {
        imagePicker = ImagePicker(
            activity,
            this
        ) { imageUri ->
            when (selectOption) {
                "avatar" -> {
                    imageViewPicture.setImageURI(imageUri)

                    val file = FileUtil.from(activity!!, imageUri)
                    uploadFileToServer(file, selectOption)
                }
                "certificate" -> {

                    imageViewCertificate.setImageURI(imageUri)
                    val file = FileUtil.from(activity!!, imageUri)
                    uploadFileToServer(file, selectOption)
                }
                else -> {

                    val file = FileUtil.from(activity!!, imageUri)
                    uploadFileToServer(file, selectOption)
                }
            }
        }


    }


    //For provide option image select gallery or camera
    private fun selectImage() {
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")

        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Add Photo!")
        builder.setItems(items) { dialog, item ->
            if (items[item] == "Take Photo") {
                userChoosenTask = "Take Photo"
                isSelectedOption = false
                openCamera()
            } else if (items[item] == "Choose from Library") {
                chooseFromGallery()
            } else if (items[item] == "Cancel") {
                isSelectedCancel = 1
                dialog.dismiss()
            }
        }
        builder.show()
    }

}