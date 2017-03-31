package com.pataleta.restfullservice.Service;


import com.pataleta.restfullservice.model.Auto;

import java.io.IOException;
import java.util.ArrayList;

public interface parsWeb {

    ArrayList<Auto> getList(String searchString) throws IOException;

}
