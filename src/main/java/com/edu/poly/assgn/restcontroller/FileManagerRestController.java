// package com.poly.edu.assgnment.restcontroller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.poly.edu.assgnment.service.FileManagerService;

// import jakarta.websocket.server.PathParam;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;



// @CrossOrigin("*")
// @RestController
// public class FileManagerRestController {
//     @Autowired
//     FileManagerService fileService;

//     @GetMapping("/rest/files/{folder}/{file}")
//     public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String file) {
//         return fileService.read(folder, file);
//     }

//     @GetMapping("/rest/files/{folder}")
//     public java.util.List<String> list(@PathVariable("folder") String folder) {
//         return fileService.list(folder);
//     }

//     @PostMapping("/rest/files/{folder}")
//     public java.util.List<String> upload(@PathVariable("folder") String folder, @PathParam("files") MultipartFile[] files) {
//         return fileService.save(folder, files);
//     }
    
//     @DeleteMapping("/rest/files/{folder}/{file}")
//     public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file){
//         fileService.delete(folder, file);
//     }


    

// }
