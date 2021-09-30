/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dogsData: List<Dog> = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val dogImage: ImageView = view!!.findViewById(R.id.dog_image)
        val dogNameText: TextView = view!!.findViewById(R.id.dog_name)
        val dogAgeText: TextView = view!!.findViewById(R.id.dog_age)
        val dogHobbiesText: TextView = view!!.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val selectedLayout = when(layout) {
            Layout.VERTICAL -> R.layout.vertical_horizontal_list_item
            Layout.HORIZONTAL -> R.layout.vertical_horizontal_list_item
            else -> R.layout.grid_list_item
        }

        val adapterLayout  = LayoutInflater.from(parent.context).inflate(selectedLayout, parent, false)

        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dogsData.size;
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {

        val dogData = dogsData[position]

        holder.dogImage.setImageResource(dogData.imageResourceId)

        holder.dogNameText.text = dogData.name

        val resources = context?.resources

        holder.dogAgeText.text = resources!!.getString(R.string.dog_age).format(dogData.age)

        holder.dogHobbiesText.text = resources!!.getString(R.string.dog_hobbies).format(dogData.hobbies)
    }
}
