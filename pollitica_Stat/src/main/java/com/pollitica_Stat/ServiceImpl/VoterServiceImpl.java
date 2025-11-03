package com.pollitica_Stat.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pollitica_Stat.Model.Prabhag;
import com.pollitica_Stat.Model.VotersDetails;
import com.pollitica_Stat.Repository.PrabhagRepository;
import com.pollitica_Stat.Repository.VoterRepository;
import com.pollitica_Stat.Service.VoterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class VoterServiceImpl implements VoterService {
private final PrabhagRepository prabhagService;
private final VoterRepository voterRepository;
	@Override
	public String saveVotersFromFile(MultipartFile file, Integer prabhagId) {
		  try {
	            Prabhag prabhag = prabhagService.findByPrabhagId(prabhagId);
	            if (prabhag == null) {
	                return "❌ Invalid Prabhag ID: " + prabhagId;
	            }

	            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
	            String line;
	            int saved = 0, skipped = 0;

	            br.readLine(); // skip header

	            while ((line = br.readLine()) != null) {

	                String[] data = line.split(",", -1);
	                if (data.length < 9) continue;

	                String voterId = data[1].trim();

	                // Skip if voter already exists
	                if (voterRepository.existsByVoterId(voterId)) {
	                    skipped++;
	                    continue;
	                }

	                VotersDetails voter = new VotersDetails();
	                voter.setVoterId(voterId);
	                voter.setVoterEnglishName(data[2]);
	                voter.setVoterMarathiName(data[3]);
	                voter.setVotersFatherEnglishName(data[4]);
	                voter.setVotersFatherMarathiName(data[5]);
	                voter.setAge(data[6]);
	                voter.setHouseNo(data[7]);
	                voter.setGender(data[8]);
	                voter.setPrabhag(prabhag);

	                voterRepository.save(voter);
	                saved++;
	            }

	            return "✅ Upload Completed\n" +
	                   "Saved: " + saved + "\n" +
	                   "Skipped (existing): " + skipped;

	        } catch (Exception e) {
	            log.error("Error uploading voters CSV", e);
	            return "❌ Error: " + e.getMessage();
	        }
	    }
}
