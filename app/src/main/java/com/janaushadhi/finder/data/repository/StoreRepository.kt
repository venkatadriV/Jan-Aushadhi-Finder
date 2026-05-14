package com.janaushadhi.finder.data.repository

import com.janaushadhi.finder.data.model.Store
import kotlin.math.*

/**
 * Repository for store locator data.
 * Uses simulated store data for Jan-Aushadhi Kendras.
 */
class StoreRepository {

    /**
     * Get Jan-Aushadhi Kendra stores near a location.
     * First tries within [radiusKm]. If none found, returns the nearest 10 stores regardless of distance.
     */
    fun getNearbyStores(userLat: Double, userLng: Double, radiusKm: Double = 50.0): List<Store> {
        val allWithDistance = getSimulatedStores().map { store ->
            store.copy(distanceKm = haversineDistance(userLat, userLng, store.latitude, store.longitude))
        }.sortedBy { it.distanceKm }

        val withinRadius = allWithDistance.filter { it.distanceKm <= radiusKm }

        // If stores found within radius, return them; otherwise return nearest 10
        return if (withinRadius.isNotEmpty()) {
            withinRadius
        } else {
            allWithDistance.take(10)
        }
    }

    /** Calculate distance between two coordinates using Haversine formula */
    private fun haversineDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        val c = 2 * asin(sqrt(a))
        return (r * c * 100).roundToInt() / 100.0
    }

    /** Simulated Jan-Aushadhi Kendras across major Indian cities */
    private fun getSimulatedStores(): List<Store> = listOf(
        // Delhi NCR
        Store(1,"Jan Aushadhi Kendra - AIIMS","AIIMS Campus, Ansari Nagar, New Delhi",28.5672,77.2100,"011-26588500",true,4.5f),
        Store(2,"Jan Aushadhi Kendra - Safdarjung","Safdarjung Hospital, New Delhi",28.5685,77.2066,"011-26707437",true,4.3f),
        Store(3,"Jan Aushadhi Kendra - GTB Nagar","GTB Hospital, Dilshad Garden, Delhi",28.6869,77.3102,"011-22586262",true,4.1f),
        Store(4,"Jan Aushadhi Kendra - Connaught Place","Block A, Connaught Place, New Delhi",28.6315,77.2167,"011-23345678",true,4.4f),
        Store(5,"Jan Aushadhi Kendra - Dwarka","Sector 12, Dwarka, New Delhi",28.5921,77.0409,"011-28031234",false,3.9f),
        Store(6,"Jan Aushadhi Kendra - Rohini","Sector 15, Rohini, New Delhi",28.7370,77.1140,"011-27565678",true,4.2f),
        Store(7,"Jan Aushadhi Kendra - Lajpat Nagar","Lajpat Nagar II, New Delhi",28.5700,77.2400,"011-29815678",true,4.0f),
        // Mumbai
        Store(8,"Jan Aushadhi Kendra - KEM Hospital","KEM Hospital, Parel, Mumbai",19.0030,72.8420,"022-24136051",true,4.6f),
        Store(9,"Jan Aushadhi Kendra - Andheri","Andheri West, Mumbai",19.1197,72.8464,"022-26281234",true,4.2f),
        Store(10,"Jan Aushadhi Kendra - Dadar","Dadar TT, Mumbai",19.0178,72.8478,"022-24111234",false,3.8f),
        Store(11,"Jan Aushadhi Kendra - Thane","Civil Hospital, Thane",19.2183,72.9781,"022-25331234",true,4.1f),
        Store(12,"Jan Aushadhi Kendra - Vashi","NMMC Hospital, Vashi, Navi Mumbai",19.0771,72.9987,"022-27891234",true,4.3f),
        // Bangalore - South
        Store(13,"Jan Aushadhi Kendra - Koramangala","Koramangala 4th Block, Bangalore",12.9352,77.6245,"080-41231234",true,4.4f),
        Store(14,"Jan Aushadhi Kendra - Jayanagar","Jayanagar 4th Block, Bangalore",12.9250,77.5938,"080-26631234",true,4.5f),
        Store(15,"Jan Aushadhi Kendra - Whitefield","ITPL Road, Whitefield, Bangalore",12.9698,77.7500,"080-28451234",false,3.7f),
        // Bangalore - North
        Store(31,"Jan Aushadhi Kendra - Yelahanka","Yelahanka New Town, Near Health Centre, Bangalore",13.1007,77.5963,"080-28561234",true,4.3f),
        Store(32,"Jan Aushadhi Kendra - Hebbal","Hebbal Kempapura, Near Esteem Mall, Bangalore",13.0358,77.5970,"080-23621234",true,4.2f),
        Store(33,"Jan Aushadhi Kendra - Thanisandra","Thanisandra Main Road, Bangalore",13.0597,77.6370,"080-29871234",true,4.1f),
        Store(34,"Jan Aushadhi Kendra - Jakkur","Jakkur Main Road, Near Jakkur Aerodrome, Bangalore",13.0727,77.6012,"080-28971234",true,4.0f),
        Store(35,"Jan Aushadhi Kendra - Sahakara Nagar","Sahakara Nagar, Near BDA Complex, Bangalore",13.0597,77.5802,"080-23541234",true,4.4f),
        Store(36,"Jan Aushadhi Kendra - Devanahalli","Devanahalli Town, Near Bus Stand, Bangalore",13.2473,77.7114,"080-27681234",true,3.9f),
        // Bangalore - Central/East/West
        Store(37,"Jan Aushadhi Kendra - Rajajinagar","Rajajinagar, Near Navrang Theatre, Bangalore",12.9910,77.5536,"080-23301234",true,4.3f),
        Store(38,"Jan Aushadhi Kendra - Marathahalli","Marathahalli Bridge, Near Kalamandir, Bangalore",12.9591,77.7009,"080-42341234",true,4.1f),
        Store(39,"Jan Aushadhi Kendra - Electronic City","Electronic City Phase 1, Bangalore",12.8456,77.6603,"080-28521234",true,4.2f),
        Store(40,"Jan Aushadhi Kendra - Malleshwaram","Malleshwaram 8th Cross, Bangalore",12.9972,77.5704,"080-23341234",true,4.5f),
        Store(41,"Jan Aushadhi Kendra - Indiranagar","Indiranagar 100 Feet Road, Bangalore",12.9784,77.6408,"080-25201234",true,4.4f),
        // Chennai
        Store(16,"Jan Aushadhi Kendra - T Nagar","T Nagar, Chennai",13.0418,80.2341,"044-24341234",true,4.3f),
        Store(17,"Jan Aushadhi Kendra - Anna Nagar","Anna Nagar West, Chennai",13.0850,80.2101,"044-26161234",true,4.1f),
        Store(18,"Jan Aushadhi Kendra - Adyar","Adyar, Chennai",13.0067,80.2572,"044-24411234",true,4.2f),
        // Kolkata
        Store(19,"Jan Aushadhi Kendra - Salt Lake","Salt Lake City, Kolkata",22.5800,88.4200,"033-23371234",true,4.0f),
        Store(20,"Jan Aushadhi Kendra - Howrah","Howrah General Hospital, Kolkata",22.5958,88.2636,"033-26681234",true,4.3f),
        // Pune
        Store(21,"Jan Aushadhi Kendra - Baner","Baner Road, Pune",18.5590,73.7868,"020-27291234",true,4.1f),
        Store(22,"Jan Aushadhi Kendra - Kothrud","Kothrud, Pune",18.5074,73.8077,"020-25381234",true,4.4f),
        // Ahmedabad
        Store(23,"Jan Aushadhi Kendra - Ashram Road","Ashram Road, Ahmedabad",23.0225,72.5714,"079-26581234",true,4.2f),
        Store(24,"Jan Aushadhi Kendra - SG Highway","SG Highway, Ahmedabad",23.0300,72.5100,"079-27451234",false,3.9f),
        // Lucknow
        Store(25,"Jan Aushadhi Kendra - Aliganj","Aliganj, Lucknow",26.8900,80.9400,"0522-23451234",true,4.0f),
        Store(26,"Jan Aushadhi Kendra - Gomti Nagar","Gomti Nagar, Lucknow",26.8467,80.9462,"0522-27891234",true,4.3f),
        // Others
        Store(27,"Jan Aushadhi Kendra - Varanasi","Lanka, Varanasi",25.2677,82.9913,"0542-23451234",true,4.1f),
        Store(28,"Jan Aushadhi Kendra - Raja Bazar","Raja Bazar, Patna",25.6100,85.1400,"0612-22341234",true,3.9f),
        Store(29,"Jan Aushadhi Kendra - Bhubaneswar","Saheed Nagar, Bhubaneswar",20.2961,85.8245,"0674-25891234",true,4.2f),
        Store(30,"Jan Aushadhi Kendra - MI Road","MI Road, Jaipur",26.9124,75.7873,"0141-23561234",true,4.4f)
    )

    /** Simulate stock check - returns random availability */
    fun checkStock(storeId: Int, medicineName: String): Boolean {
        return (storeId + medicineName.length) % 3 != 0
    }
}
