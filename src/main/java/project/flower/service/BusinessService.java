package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.flower.FileHandler;
import project.flower.domain.flower.bouquet.BouquetImage;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.FlowerSingleForm;
import project.flower.domain.member.Member;
import project.flower.file.FileStore;
import project.flower.file.UploadFile;
import project.flower.repository.BouquetImageRepository;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;
import project.flower.repository.FlowerSingleRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    private final FlowerBouquetRepository flowerBouquetRepository;
    private final FlowerSingleRepository flowerSingleRepository;

    private final BouquetImageRepository bouquetImageRepository;

    private final FileHandler fileHandler;
    private final FileStore fileStore;
    public Long registerBusiness(BusinessForm form, Member member){

        Business business = form.toEntity(member);
        business.setMember(member);

        return businessRepository.save(business).getId();
    }
    @Transactional
    public Business findBusiness(Long id){

        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 꽃집이 존재하지 않습니다."));

        return business;
    }

    @Transactional
    public Map<Long, Business> findBusinessMap(){
        Map<Long, Business> businessMap = new HashMap<>();
        List<Business> businessList = businessRepository.findAll();
        for (Business business : businessList) {
            businessMap.put(business.getId(), business);
        }
        return businessMap;
    }


    @Transactional
    public Long registerBouquet(FlowerBouquetForm form, Business business) throws Exception {


        /*LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);

        String absolutePath = new File("").getAbsolutePath() + File.separator;

        String path ="src" + File.separator +"main"+ File.separator +"resources"+ File.separator +"static"+ File.separator +"files" + File.separator + current_date;
        File file = new File(path);

        System.out.println("absolutePath = " + absolutePath);
        System.out.println("path" + path);

        // 디렉터리가 존재하지 않을 경우
        if(!file.exists()) {
            boolean wasSuccessful = file.mkdirs();
            // 디렉터리 생성에 실패했을 경우
            if(!wasSuccessful)
                System.out.println("file: was not successful");
        }

        String originalFileExtension = "";
        String contentType = imgFile.getContentType();

        // 확장자명이 존재하지 않을 경우 처리 x
        if (ObjectUtils.isEmpty(contentType)) {

        } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
            if (contentType.contains("image/jpeg"))
                originalFileExtension = ".jpg";
            else if (contentType.contains("image/png"))
                originalFileExtension = ".png";
            else{

            }  // 다른 확장자일 경우 처리 x

        }

        String new_file_name = String.valueOf(System.nanoTime()) + originalFileExtension;


        file = new File(absolutePath + path + File.separator + new_file_name);
        imgFile.transferTo(file);




        form.setImgName(imgFile.getOriginalFilename());
        form.setImgPath( absolutePath + path + File.separator + new_file_name);*/

        UploadFile storeImageFIle = fileStore.storeFile(form.getImgFile());
        String storeFileName = storeImageFIle.getStoreFileName();
        String uploadFileName = storeImageFIle.getUploadFileName();





        form.setImgName(storeFileName);
        form.setImgPath(uploadFileName);

        FlowerBouquet bouquet = form.toEntity(business);
        bouquet.setBusiness(business);





        /*List<BouquetImage> bouquetImageList = fileHandler.parseFileInfo(files, bouquet);
        if(!bouquetImageList.isEmpty()) {
            List<BouquetImage> imageBeans = new ArrayList<>();
            for(BouquetImage bouquetImage : bouquetImageList) {
                // 파일을 DB에 저장
                imageBeans.add(bouquetImageRepository.save(bouquetImage));
                //bouquetImage.setFlowerBouquet(bouquet);
                //bouquet.addImage(bouquetImageRepository.save(bouquetImage));
            }
        }*/
        return flowerBouquetRepository.save(bouquet).getId();
    }

    @Transactional
    public Long registerSingle(FlowerSingleForm form, Business business) throws IOException {

        UploadFile storeImageFIle = fileStore.storeFile(form.getImgFile());
        String storeFileName = storeImageFIle.getStoreFileName();
        String uploadFileName = storeImageFIle.getUploadFileName();


        form.setImgName(storeFileName);
        form.setImgPath(uploadFileName);

        FlowerSingle single = form.toEntity(business);
        single.setBusiness(business);

        return flowerSingleRepository.save(single).getId();
    }

}
