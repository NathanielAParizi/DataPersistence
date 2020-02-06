package com.example.datapersistence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter(val personList: ArrayList<Person>,  val callback: PersonCallback) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.person_item, parent,
                false
            )
        )

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.populatePersonItem(personList[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populatePersonItem(person: Person) {
            itemView.txtFirstName.text = person.firstName
            itemView.txtLastName.text = person.lastName
            itemView.txtSSN.text = person.ssn
            itemView.setOnClickListener{ callback.passPerson(person)}
        }
    }

}