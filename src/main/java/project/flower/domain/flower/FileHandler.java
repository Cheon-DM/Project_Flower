package project.flower.domain.flower;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import project.flower.domain.flower.bouquet.BouquetImage;
import project.flower.domain.flower.bouquet.BouquetImageDto;
import project.flower.domain.flower.bouquet.FlowerBouquet;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler { //List<MultipartFile> -> List<BouquwtImage> 로 변환

    public List<BouquetImage> parseFileInfo(List<MultipartFile> multipartFiles, FlowerBouquet bouquet)throws Exception {
        //반환할 파일 리스트
        List<BouquetImage> bouquetImageList = new ArrayList<>();
        //전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            String path = "images" + File.separator + current_date;
            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {

                // 파일의 확장자 추출
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 확장자명이 존재하지 않을 경우 처리 x
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if (contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if (contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else  // 다른 확장자일 경우 처리 x
                        break;
                }

                // 파일명 중복 피하고자 나노초까지 얻어와 지정
                String new_file_name = System.nanoTime() + originalFileExtension;

                BouquetImageDto bouquetImageDto = BouquetImageDto.builder()
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();

                /*BouquetImage bouquetImage = new BouquetImage(
                        bouquetImageDto.getOrigFileName(),
                        bouquetImageDto.getFilePath(),
                        bouquetImageDto.getFileSize()
                );*/

                BouquetImage bouquetImage = BouquetImage.builder()
                        .bouquet(bouquet)
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();

                bouquetImageList.add(bouquetImage);

                file = new File(absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);

                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return bouquetImageList;
    }
}
