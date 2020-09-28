package library.web;

import library.model.service.UserServiceModel;
import library.model.views.AuthorViewModel;
import library.model.views.UserViewModel;
import library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/user/all")
    @ResponseBody
    public List<UserViewModel> getAllAuthors(){
        List<UserServiceModel> userServiceModelsList = this.userService.getAllUser();
        return userServiceModelsList.stream().map(userServiceModel -> this.modelMapper
                .map(userServiceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }
}