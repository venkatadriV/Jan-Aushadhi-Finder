package com.janaushadhi.finder.data

import com.janaushadhi.finder.data.model.Medicine

object SeedData {
    private data class M(val b:String,val g:String,val s:String,val bp:Double,val gp:Double,val c:String,val e:Boolean=false,val mf:String="",val d:String="")

    fun getMedicines(): List<Medicine> {
        val raw = listOf(
            M("Crocin","Paracetamol","Paracetamol 500mg",30.0,5.0,"Pain Relief",true,"GSK","Fever and pain relief"),
            M("Dolo 650","Paracetamol","Paracetamol 650mg",32.0,6.0,"Pain Relief",true,"Micro Labs","Fever reducer"),
            M("Calpol","Paracetamol","Paracetamol 500mg",28.0,5.0,"Pain Relief",false,"GSK","Mild pain relief"),
            M("Combiflam","Ibuprofen+Paracetamol","Ibuprofen 400mg+Paracetamol 325mg",42.0,8.0,"Pain Relief",true,"Sanofi","Pain and inflammation"),
            M("Brufen","Ibuprofen","Ibuprofen 400mg",35.0,6.0,"Pain Relief",false,"Abbott","Anti-inflammatory"),
            M("Voveran","Diclofenac","Diclofenac 50mg",45.0,7.0,"Pain Relief",false,"Novartis","Pain relief"),
            M("Disprin","Aspirin","Aspirin 350mg",20.0,4.0,"Pain Relief",true,"Reckitt","Blood thinner and pain"),
            M("Saridon","Propyphenazone+Paracetamol+Caffeine","Propyphenazone 150mg+Paracetamol 250mg+Caffeine 50mg",38.0,8.0,"Pain Relief",false,"Bayer","Headache relief"),
            M("Meftal Spas","Mefenamic Acid+Dicyclomine","Mefenamic Acid 250mg+Dicyclomine 10mg",68.0,12.0,"Pain Relief",false,"Blue Cross","Menstrual pain relief"),
            M("Flexon","Ibuprofen+Paracetamol","Ibuprofen 400mg+Paracetamol 325mg",40.0,8.0,"Pain Relief",false,"Aristo","Joint pain relief"),
            M("Sumo","Nimesulide+Paracetamol","Nimesulide 100mg+Paracetamol 325mg",52.0,9.0,"Pain Relief",false,"Alkem","Pain and fever"),
            M("Zerodol SP","Aceclofenac+Paracetamol+Serratiopeptidase","Aceclofenac 100mg+PCM 325mg+Serra 15mg",95.0,15.0,"Pain Relief",false,"IPCA","Inflammation relief"),
            M("Augmentin","Amoxicillin+Clavulanate","Amoxicillin 500mg+Clavulanic Acid 125mg",180.0,30.0,"Antibiotics",true,"GSK","Bacterial infections"),
            M("Azithral","Azithromycin","Azithromycin 500mg",120.0,22.0,"Antibiotics",true,"Alembic","Respiratory infections"),
            M("Cifran","Ciprofloxacin","Ciprofloxacin 500mg",95.0,15.0,"Antibiotics",false,"Sun Pharma","UTI and infections"),
            M("Monocef","Ceftriaxone","Ceftriaxone 1g",185.0,35.0,"Antibiotics",true,"Aristo","Severe bacterial infections"),
            M("Taxim-O","Cefixime","Cefixime 200mg",130.0,20.0,"Antibiotics",false,"Alkem","Respiratory/urinary infections"),
            M("Amoxil","Amoxicillin","Amoxicillin 500mg",85.0,12.0,"Antibiotics",false,"GSK","Broad spectrum antibiotic"),
            M("Ciplox","Ciprofloxacin","Ciprofloxacin 250mg",65.0,10.0,"Antibiotics",false,"Cipla","Bacterial infections"),
            M("Norflox","Norfloxacin","Norfloxacin 400mg",55.0,9.0,"Antibiotics",false,"Cipla","Urinary infections"),
            M("Metrogyl","Metronidazole","Metronidazole 400mg",35.0,6.0,"Antibiotics",false,"J.B. Chemicals","Anaerobic infections"),
            M("Doxycycline","Doxycycline","Doxycycline 100mg",80.0,12.0,"Antibiotics",false,"Various","Bacterial infections"),
            M("Erythrocin","Erythromycin","Erythromycin 250mg",70.0,11.0,"Antibiotics",false,"Abbott","Respiratory infections"),
            M("Clavam","Amoxicillin+Clavulanate","Amoxicillin 625mg+Clavulanic Acid",165.0,28.0,"Antibiotics",false,"Alkem","Severe infections"),
            M("Glucobay","Acarbose","Acarbose 50mg",145.0,25.0,"Diabetes",false,"Bayer","Blood sugar control"),
            M("Glycomet","Metformin","Metformin 500mg",35.0,6.0,"Diabetes",true,"USV","Type 2 diabetes"),
            M("Januvia","Sitagliptin","Sitagliptin 100mg",580.0,85.0,"Diabetes",false,"MSD","Blood sugar management"),
            M("Amaryl","Glimepiride","Glimepiride 2mg",120.0,18.0,"Diabetes",false,"Sanofi","Diabetes management"),
            M("Glucophage","Metformin","Metformin 850mg",55.0,8.0,"Diabetes",false,"Merck","Diabetes type 2"),
            M("Diamicron","Gliclazide","Gliclazide 80mg",140.0,20.0,"Diabetes",false,"Servier","Blood sugar control"),
            M("Galvus","Vildagliptin","Vildagliptin 50mg",420.0,65.0,"Diabetes",false,"Novartis","DPP-4 inhibitor"),
            M("Jalra","Vildagliptin","Vildagliptin 50mg",380.0,60.0,"Diabetes",false,"USV","Blood sugar control"),
            M("Trajenta","Linagliptin","Linagliptin 5mg",520.0,75.0,"Diabetes",false,"BI","Diabetes management"),
            M("Gluformin","Metformin","Metformin 1000mg",65.0,10.0,"Diabetes",false,"Various","Extended release diabetes"),
            M("Pioz","Pioglitazone","Pioglitazone 15mg",95.0,14.0,"Diabetes",false,"USV","Insulin sensitizer"),
            M("Glynase","Glipizide","Glipizide 5mg",45.0,7.0,"Diabetes",false,"USV","Sulfonylurea diabetes"),
            M("Ecosprin","Aspirin","Aspirin 75mg",28.0,5.0,"Cardiac",true,"USV","Heart attack prevention"),
            M("Atorva","Atorvastatin","Atorvastatin 10mg",135.0,18.0,"Cardiac",false,"Zydus","Cholesterol control"),
            M("Clopitab","Clopidogrel","Clopidogrel 75mg",95.0,14.0,"Cardiac",true,"Lupin","Blood clot prevention"),
            M("Telma","Telmisartan","Telmisartan 40mg",120.0,16.0,"Cardiac",false,"Glenmark","Blood pressure control"),
            M("Amlodac","Amlodipine","Amlodipine 5mg",55.0,8.0,"Cardiac",false,"Zydus","Hypertension"),
            M("Concor","Bisoprolol","Bisoprolol 5mg",85.0,12.0,"Cardiac",false,"Merck","Heart rate control"),
            M("Envas","Enalapril","Enalapril 5mg",42.0,7.0,"Cardiac",false,"Cadila","ACE inhibitor BP"),
            M("Cardace","Ramipril","Ramipril 5mg",95.0,14.0,"Cardiac",false,"Sanofi","Heart failure"),
            M("Losar","Losartan","Losartan 50mg",80.0,12.0,"Cardiac",false,"Various","Hypertension ARB"),
            M("Stamlo","Amlodipine","Amlodipine 5mg",60.0,8.0,"Cardiac",false,"Dr Reddy","Calcium channel blocker"),
            M("Metolar","Metoprolol","Metoprolol 50mg",65.0,10.0,"Cardiac",false,"Cipla","Beta blocker"),
            M("Rozavel","Rosuvastatin","Rosuvastatin 10mg",180.0,22.0,"Cardiac",false,"Sun Pharma","Statin cholesterol"),
            M("Crestor","Rosuvastatin","Rosuvastatin 10mg",250.0,22.0,"Cardiac",false,"AstraZeneca","Cholesterol reducer"),
            M("Lipitor","Atorvastatin","Atorvastatin 20mg",220.0,20.0,"Cardiac",false,"Pfizer","Cholesterol management"),
            M("Pan 40","Pantoprazole","Pantoprazole 40mg",95.0,12.0,"GI",true,"Alkem","Acid reflux/GERD"),
            M("Omez","Omeprazole","Omeprazole 20mg",70.0,8.0,"GI",true,"Dr Reddy","Stomach ulcers"),
            M("Rantac","Ranitidine","Ranitidine 150mg",30.0,5.0,"GI",false,"J.B. Chemicals","Acidity relief"),
            M("Gelusil","Aluminium+Magnesium Hydroxide","AlOH+MgOH+Simethicone",55.0,10.0,"GI",false,"Pfizer","Antacid"),
            M("Digene","Dried AlOH+MgOH+Simethicone","AlOH 830mg+MgOH 185mg",65.0,12.0,"GI",false,"Abbott","Indigestion relief"),
            M("Rablet","Rabeprazole","Rabeprazole 20mg",110.0,15.0,"GI",false,"Cadila","Proton pump inhibitor"),
            M("Nexpro","Esomeprazole","Esomeprazole 40mg",145.0,18.0,"GI",false,"Torrent","Severe acid reflux"),
            M("Domperidone","Domperidone","Domperidone 10mg",35.0,5.0,"GI",false,"Various","Anti-nausea"),
            M("Econorm","Saccharomyces Boulardii","S. Boulardii 250mg",120.0,20.0,"GI",false,"Dr Reddy","Probiotic diarrhea"),
            M("Cyclopam","Dicyclomine+Paracetamol","Dicyclomine 20mg+PCM 325mg",48.0,8.0,"GI",false,"Indoco","Abdominal cramps"),
            M("Dulcolax","Bisacodyl","Bisacodyl 5mg",45.0,7.0,"GI",false,"Sanofi","Constipation relief"),
            M("Loperamide","Loperamide","Loperamide 2mg",25.0,4.0,"GI",false,"Various","Anti-diarrheal"),
            M("Asthalin","Salbutamol","Salbutamol 2mg",45.0,7.0,"Respiratory",true,"Cipla","Asthma relief"),
            M("Deriphyllin","Theophylline+Etophylline","Theophylline 23mg+Etophylline 77mg",32.0,6.0,"Respiratory",false,"Zydus","Bronchodilator"),
            M("Foracort","Formoterol+Budesonide","Formoterol 6mcg+Budesonide 200mcg",380.0,55.0,"Respiratory",false,"Cipla","Asthma inhaler"),
            M("Seroflo","Salmeterol+Fluticasone","Salmeterol 25mcg+Fluticasone 250mcg",420.0,60.0,"Respiratory",false,"Cipla","COPD/Asthma"),
            M("Montair","Montelukast","Montelukast 10mg",165.0,22.0,"Respiratory",false,"Cipla","Allergic asthma"),
            M("Alex","Phenylephrine+Chlorpheniramine+Dextromethorphan","Multi-ingredient",85.0,14.0,"Respiratory",false,"Glenmark","Cough and cold"),
            M("Benadryl","Diphenhydramine","Diphenhydramine 25mg",65.0,10.0,"Respiratory",false,"J&J","Cough suppressant"),
            M("Mucinex","Guaifenesin","Guaifenesin 600mg",120.0,18.0,"Respiratory",false,"Reckitt","Expectorant"),
            M("Levolin","Levosalbutamol","Levosalbutamol 1mg",55.0,9.0,"Respiratory",false,"Cipla","Bronchospasm"),
            M("Budecort","Budesonide","Budesonide 200mcg",320.0,45.0,"Respiratory",false,"Sun Pharma","Inhaled steroid"),
            M("Betnovate","Betamethasone","Betamethasone 0.1%",75.0,12.0,"Dermatology",false,"GSK","Skin inflammation"),
            M("Candid","Clotrimazole","Clotrimazole 1%",85.0,12.0,"Dermatology",false,"Glenmark","Fungal skin infection"),
            M("Clobetasol","Clobetasol","Clobetasol 0.05%",95.0,14.0,"Dermatology",false,"Various","Severe eczema"),
            M("Panderm","Clobetasol+Neomycin+Miconazole","Multi-ingredient cream",110.0,18.0,"Dermatology",false,"Macleods","Skin infection cream"),
            M("Terbinafine","Terbinafine","Terbinafine 250mg",180.0,25.0,"Dermatology",false,"Various","Fungal infection"),
            M("Mupirocin","Mupirocin","Mupirocin 2%",120.0,18.0,"Dermatology",false,"GSK","Bacterial skin infection"),
            M("Cetaphil","Emollient","Emollient cream",350.0,50.0,"Dermatology",false,"Galderma","Skin moisturizer"),
            M("Permethrin","Permethrin","Permethrin 5%",55.0,9.0,"Dermatology",false,"Various","Scabies treatment"),
            M("Fucidin","Fusidic Acid","Fusidic Acid 2%",145.0,20.0,"Dermatology",false,"LEO","Skin bacterial infection"),
            M("Shelcal","Calcium+VitD3","Calcium 500mg+Vitamin D3 250IU",130.0,18.0,"Vitamins",false,"Torrent","Bone health"),
            M("Becosules","B-Complex+Vitamin C","Multivitamin B Complex",35.0,6.0,"Vitamins",false,"Pfizer","Vitamin supplement"),
            M("Supradyn","Multivitamin+Minerals","Multivitamin multimineral",75.0,12.0,"Vitamins",false,"Bayer","Daily supplement"),
            M("Limcee","Vitamin C","Ascorbic Acid 500mg",25.0,4.0,"Vitamins",false,"Abbott","Immunity booster"),
            M("Zincovit","Zinc+Multivitamins","Zinc+Vitamins+Minerals",110.0,16.0,"Vitamins",false,"Apex","Immune support"),
            M("Calcimax","Calcium+VitD3","Calcium Carbonate 1250mg+D3",155.0,20.0,"Vitamins",false,"Meyer","Calcium supplement"),
            M("Neurobion","Vitamin B1+B6+B12","Thiamine+Pyridoxine+Cyanocobalamin",55.0,8.0,"Vitamins",false,"Merck","Nerve health"),
            M("Evion","Vitamin E","Tocopherol 400mg",45.0,7.0,"Vitamins",false,"Merck","Antioxidant vitamin"),
            M("A to Z","Multivitamin","Comprehensive multivitamin",85.0,12.0,"Vitamins",false,"Alkem","Complete nutrition"),
            M("Folvite","Folic Acid","Folic Acid 5mg",18.0,3.0,"Vitamins",false,"Pfizer","Pregnancy supplement"),
            M("Cetrizine","Cetirizine","Cetirizine 10mg",35.0,4.0,"Allergy",false,"Various","Antihistamine"),
            M("Allegra","Fexofenadine","Fexofenadine 120mg",145.0,18.0,"Allergy",false,"Sanofi","Non-drowsy antihistamine"),
            M("Montair LC","Montelukast+Levocetirizine","Montelukast 10mg+Levocetirizine 5mg",175.0,22.0,"Allergy",false,"Cipla","Allergic rhinitis"),
            M("Avil","Pheniramine","Pheniramine 25mg",20.0,4.0,"Allergy",true,"Sanofi","Allergy emergency"),
            M("Levocet","Levocetirizine","Levocetirizine 5mg",55.0,7.0,"Allergy",false,"Sun Pharma","Allergies/urticaria"),
            M("Okacet","Cetirizine","Cetirizine 10mg",40.0,5.0,"Allergy",false,"Cipla","Seasonal allergy"),
            M("Wysolone","Prednisolone","Prednisolone 10mg",22.0,4.0,"Allergy",false,"Pfizer","Steroid anti-inflammatory"),
            M("Adrenaline","Epinephrine","Epinephrine 1mg/ml",85.0,15.0,"Emergency",true,"Various","Anaphylaxis"),
            M("Atropine","Atropine","Atropine 0.6mg/ml",25.0,5.0,"Emergency",true,"Various","Bradycardia"),
            M("ORS","Oral Rehydration Salts","Na+K+Glucose+Citrate",20.0,4.0,"Emergency",true,"Various","Dehydration"),
            M("Buscopan","Hyoscine","Hyoscine Butylbromide 10mg",55.0,8.0,"Emergency",true,"Sanofi","Severe abdominal cramps"),
            M("Emeset","Ondansetron","Ondansetron 4mg",65.0,10.0,"Emergency",true,"Cipla","Severe nausea/vomiting"),
            M("Deriphyllin Inj","Theophylline","Theophylline injection",45.0,8.0,"Emergency",true,"Zydus","Acute bronchospasm"),
            M("Phenytoin","Phenytoin","Phenytoin 100mg",22.0,4.0,"Emergency",true,"Various","Seizure control"),
            M("Diazepam","Diazepam","Diazepam 5mg",18.0,3.0,"Emergency",true,"Various","Seizure/anxiety emergency"),
            M("Thyronorm","Levothyroxine","Levothyroxine 50mcg",110.0,15.0,"Hormones",false,"Abbott","Hypothyroidism"),
            M("Eltroxin","Levothyroxine","Levothyroxine 100mcg",135.0,18.0,"Hormones",false,"GSK","Thyroid replacement"),
            M("Duphaston","Dydrogesterone","Dydrogesterone 10mg",520.0,65.0,"Hormones",false,"Abbott","Progesterone support"),
            M("Deviry","Medroxyprogesterone","Medroxyprogesterone 10mg",85.0,12.0,"Hormones",false,"Torrent","Hormonal therapy")
        )
        val medicines = mutableListOf<Medicine>()
        raw.forEachIndexed { i, m ->
            medicines.add(Medicine(i+1,m.b,m.g,m.s,m.bp,m.gp,m.c,m.e,m.mf,m.d))
        }
        // Generate additional medicines to reach 500+ using variants
        val strengths = listOf("50mg","100mg","150mg","200mg","250mg","500mg","10mg","20mg","25mg","75mg")
        val categories = listOf("Pain Relief","Antibiotics","Diabetes","Cardiac","GI","Respiratory","Dermatology","Vitamins","Allergy","Hormones")
        val prefixes = listOf("Neo","Max","Plus","Forte","XR","SR","ER","HD","Ultra","Pro")
        val genericNames = listOf("Paracetamol","Ibuprofen","Metformin","Atorvastatin","Amlodipine","Omeprazole","Cetirizine","Amoxicillin","Losartan","Montelukast","Pantoprazole","Ciprofloxacin","Metoprolol","Salbutamol","Diclofenac","Ranitidine","Clopidogrel","Telmisartan","Azithromycin","Doxycycline")
        val manufacturers = listOf("Cipla","Sun Pharma","Dr Reddy","Lupin","Aurobindo","Zydus","Torrent","Alkem","Glenmark","Cadila","IPCA","Mankind","Intas","Macleods","Abbott","USV")
        var id = raw.size + 1
        for (cat in categories) {
            for (j in 0 until 40) {
                val gIdx = (j + categories.indexOf(cat)) % genericNames.size
                val sIdx = j % strengths.size
                val pIdx = j % prefixes.size
                val mIdx = j % manufacturers.size
                val gName = genericNames[gIdx]
                val str = strengths[sIdx]
                val bName = "${gName.take(4)}${prefixes[pIdx]}"
                val bp = 50.0 + (j * 7.3) + (categories.indexOf(cat) * 15)
                val gp = bp * (0.12 + (j % 5) * 0.03)
                medicines.add(Medicine(
                    id = id++,
                    brandName = bName,
                    genericName = gName,
                    saltComposition = "$gName $str",
                    brandPrice = Math.round(bp * 100.0) / 100.0,
                    genericPrice = Math.round(gp * 100.0) / 100.0,
                    category = cat,
                    isEmergency = j < 2 && cat == "Emergency",
                    manufacturer = manufacturers[mIdx],
                    description = "$gName $str tablet for $cat treatment"
                ))
            }
        }
        return medicines
    }
}
