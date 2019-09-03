/*
 * Copyright 2019, The Android Open Source Project
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
 *
 */

package kotlinbootcamp.marsrealestate.network

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class MarsProperty (
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
) : Parcelable {

    constructor(source: Parcel): this(source.readString()!!, source.readString()!!, source.readString()!!, source.readDouble())

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(this.id)
        p0?.writeString(this.imgSrcUrl)
        p0?.writeString(this.type)
        p0?.writeDouble(this.price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<MarsProperty> = object : Parcelable.Creator<MarsProperty> {
            override fun createFromParcel(source: Parcel): MarsProperty {
                return MarsProperty(source)
            }

            override fun newArray(size: Int): Array<MarsProperty?> {
                return arrayOfNulls(size)
            }
        }
    }

    val isRental
        get() = type == "rent"
}
