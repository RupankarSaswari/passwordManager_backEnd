package com.example.Account.Home;

import com.example.Account.JDBC.HomeRepo;
import com.example.Account.JDBC.LoginRepo;
import com.example.Account.Json.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/api")
@RestController
public class HomeController {

    @Autowired
    private HomeRepo homeRepo;
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private HomeService homeService;

    JsonConverter jsonConverter = new JsonConverter();

    @PostMapping("/homeControllerAllData")
    public List<HomeModel> homeControllerAllData(@RequestParam String username){

        return (homeRepo.getAllHomeData(username+"Home"));
    }
    @PostMapping("/homeControllerFevData")
    public List<HomeModel> homeControllerFevData(@RequestParam String username){
        return (homeRepo.getAllFavData(username+"Home"));
    }
    @PostMapping("/homeControllerDeleteData")
    public List<HomeModel> homeControllerDeleteData(@RequestParam String username){
        return (homeRepo.getAllDeleteData(username+"Home"));
    }
    @PostMapping("/addHomeData")
    public String addHomeData(@RequestParam String originalUsername,
                              @RequestParam String name,
                              @RequestParam String userName,
                              @RequestParam String password,
                              @RequestParam String favourite) throws
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

      String rawData = homeService.homeServiceSaveData(originalUsername,name,userName,password,favourite);

      return jsonConverter.jsonConverter(rawData);

    }

    @PostMapping("/homeControllerDropTable")
    public String homeControllerDropTable(@RequestParam String username){

       return jsonConverter.jsonConverter(loginRepo.dropTable(username));// have to send the actual table name

    }

    @PostMapping("/homeControllerEdit")
    public String homeControllerEdit(@RequestParam String table,
                                     @RequestParam String userName,
                                     @RequestParam String platformName,
                                     @RequestParam String columnName,
                                     @RequestParam String newValue){

        boolean success = homeRepo.updateColumnValue(table,userName,platformName,columnName,newValue);
        return success ? jsonConverter.jsonConverter("Update successful") : jsonConverter.jsonConverter("Update failed");

    }

    @PostMapping("/deleteRow")
    public String deleteRow(@RequestParam String tableName,
                            @RequestParam String username) {

        boolean success = homeRepo.deleteRowIfMarkedForDeletion(tableName, username);

        return success ? jsonConverter.jsonConverter("Deletion successful") : jsonConverter.jsonConverter("Deletion failed");
    }

}
