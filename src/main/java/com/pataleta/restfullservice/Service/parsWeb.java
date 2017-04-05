package com.pataleta.restfullservice.Service;



import com.pataleta.restfullservice.model.SparepartEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public interface parsWeb {

     HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException;

     HashSet<SparepartEntity> getListByArticle(String article) throws IOException ;

     HashSet<SparepartEntity> getListByCode(String code) throws IOException;

}
