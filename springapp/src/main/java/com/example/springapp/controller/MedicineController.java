package com.example.springapp.controller;

import com.example.springapp.model.Medicine;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final Map<Integer, Medicine> medicineMap = new HashMap<>();
    private int nextMedicineId = 1;

    @PostMapping
    public boolean addMedicine(@RequestBody Medicine medicine) {
        // Generate a unique ID for the medicine
        medicine.setMedicineId(nextMedicineId++);
        
        // Store the medicine in the map with its ID as the key
        medicineMap.put(medicine.getMedicineId(), medicine);
        
        return true;
    }

    @PutMapping("/{medicineId}")
    public Medicine updateMedicine(@PathVariable int medicineId, @RequestBody Medicine updatedMedicine) {
        if (medicineMap.containsKey(medicineId)) {
            // Update the existing medicine with the new data
            Medicine existingMedicine = medicineMap.get(medicineId);
            existingMedicine.setMedicineName(updatedMedicine.getMedicineName());
            existingMedicine.setPrice(updatedMedicine.getPrice());
            existingMedicine.setQuantity(updatedMedicine.getQuantity());
            existingMedicine.setDescription(updatedMedicine.getDescription());

            // Return the updated medicine
            return existingMedicine;
        } else {
            // Medicine with the specified ID not found
            return null;
        }
    }
}
