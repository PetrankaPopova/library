package library.service.imp;

import library.error.exception.custom.UserNotFoundException;
import library.error.exception.custom.UserPasswordsNotMatchException;
import library.error.exception.custom.UserWithUsernameAlreadyExistException;
import library.model.entity.User;
import library.model.service.BookServiceModel;
import library.model.service.UserEditServiceModel;
import library.model.service.UserServiceModel;
import library.repository.BookRepository;
import library.repository.RoleRepository;
import library.repository.UserRepository;
import library.service.RoleService;
import library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService,
                          RoleRepository roleRepository, BookRepository bookRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public UserServiceImp(UserRepository mockedUserRepository, ModelMapper modelMapper, UserRepository userRepository, ModelMapper modelMapper1, RoleService roleService, RoleRepository roleRepository, BookRepository bookRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper1;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        User returnedUserFromDb = this.userRepository.findUserByUsername(userServiceModel.getUsername()).orElse(null);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (this.userRepository.count() == 0) {
            //this.roleService.seedRolesToDb();
            user.setAuthorities(this.roleRepository.findAll());
        } else {
            user.setAuthorities(new ArrayList<>());
            user.getAuthorities().add(this.roleRepository.findByAuthority("USER").orElse(null));
        }
        this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(user, (Type) UserServiceModel.class);

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User findedUser = this.userRepository.findUserByUsernameAndPassword(username, password).orElse(null);
        return findedUser == null ? null : this.modelMapper.map(findedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) throws UserNotFoundException {
        User u = this.userRepository.findByUsername(username).orElse(null);
        if(u == null){
            throw new UserNotFoundException("User not exist");
        }
        return this.modelMapper.map(u, UserServiceModel.class);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserEditServiceModel editUserProfile(UserEditServiceModel userEditServiceModel, String oldPassword)
            throws UserPasswordsNotMatchException, UserWithUsernameAlreadyExistException {
        return null;
    }

    @Override
    public List<BookServiceModel> getAllBoughtBooks() {
        return null;
    }

    @Override
    public List<UserServiceModel> findAllUser() {
        return this.userRepository.findAll().
                stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = this.userRepository.findByUsername(username).orElse(null);
        if (findUser == null) {
            throw new UsernameNotFoundException("User does not exists!");
        }
        return findUser;
    }

    public UserServiceModel addNewUser(UserServiceModel userServiceModel) {
       User foundedUser = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);
        if (foundedUser != null) return null;
        User user = this.modelMapper.map(userServiceModel, User.class);
        User userReturnFromDb = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(userReturnFromDb, UserServiceModel.class);
    }
}

    /*
    @Override
    public String registerUser(UserServiceModel userServiceModel) {
        String msg = "";
        User user = this.modelMapper.map(userServiceModel, User.class);
        User returnedUserFromDb = this.userRepository.findUserByUsername(userServiceModel.getUsername()).orElse(null);
        if (userRepository.existsByUsername(userServiceModel.getUsername())) {
            msg = USER_NAME_EXISTS_MESSAGE;
        }
        if (userRepository.existsByEmail(userServiceModel.getEmail())) {
            throw new AlreadyExistsException("email", USER_EMAIL_EXISTS_MASSAGE);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesToDb();
            //       user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            //    user.setAuthorities(new HashSet<>());
            //     user.getAuthorities().add(this.roleRepository.findByAuthority("USER").orElse(null));
        }
        this.userRepository.saveAndFlush(user);
        return null;

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User findedUser = this.userRepository.findUserByUsernameAndPassword(username, password).orElse(null);
        return findedUser == null ? null : this.modelMapper.map(findedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User findedUser = this.userRepository.findUserByUsername(username).orElse(null);
        return this.modelMapper.map(findedUser, UserServiceModel.class);
    }


    @Override
    public void deleteProductById(String id) {
        //this.userRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        //this.userRepository.deleteAll();
    }

    @Override
    public void buyProduct(String productId) {
        /*Book b = this.bookRepository.findById(productId).orElse(null);
        throw new UserWithThisNameIsNotLogged("User with this name is not logged!");
    }


    @Override
    public UserEditServiceModel editUserProfile(UserEditServiceModel userEditServiceModel, String oldPassword) {
        return null;
    }

    @Override
    public UserServiceModel findById(String userId) {
        UserServiceModel userServiceModel = null;
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null){
            userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        }
        return userServiceModel;
    }

    @Override
    public void buyBook(String bookId, String loggedUserStr) {
        //todo
    }


    @Override
    public void removeAllProductCart(String loggedUserStr) {
        //todo
    }

    @Override
    public List<BookServiceModel> getAllBoughtBooks() {
        return null;
    }
}


// @Override
// public List<BookViewModel> getAllBoughtBooks() {
//    String userStr = this.tools.getLoggedUser();
//  User user = this.userRepository.findByUsername(userStr)
//    .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));
// return user.getBoughtBooks().stream()
//    .map(pr -> this.modelMapper.map(pr, BookViewModel.class))
//   .collect(Collectors.toList());

//  }

/*
Book b = this.bookRepository.findById(bookId).orElse(null);
        String userStr = this.tools.getLoggedUser();
        if ("anonymousUser".equals(userStr)) {
            throw new UserWithThisNameIsNotLogged("User with this name is not logged!");
        }
        User u = this.userRepository.findByUsername(userStr).orElse(null);
        if (u == null) {
            throw new UserIsNullOrCartIsNullException("User is null or car is null exception!");
        }
        u.getBoughtBook().add(b);
        this.userRepository.saveAndFlush(u);

        //   User u = this.userRepository.findByUsername(this.tools.getLoggedUser()).orElse(null);
        //if (u != null) {

      //      if (userEditServiceModel.getPassword() != null && !"".equals(userEditServiceModel.getPassword()) &&
                //    userEditServiceModel.getConfirmPassword()!=null && !"".equals(userEditServiceModel.getConfirmPassword())){
             //   u.setPassword(this.bCryptPasswordEncoder.encode(userEditServiceModel.getPassword()));
          //  }


         //   this.userRepository.saveAndFlush(u);

     //   } else {
        //    throw new UserWithUsernameAlreadyExistException("User is not Exist (internal error)!");
      //  }
 */