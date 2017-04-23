package com.pataleta.restfullservice.Service;



import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface parsWeb {

     HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException;

     HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithoutAnalogs(String article) throws IOException ;

     HashSet<SparepartEntity> getListByArticleAndBrandWithoutAnalogs(String article, String brand) throws IOException ;

     Set<String> getListOfBrands(String article);

     HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithAnalogs(String article) throws IOException;

     HashSet<SparepartEntity> getListByArticleAndBrandWithAnalogs(String article, String brand) throws IOException;
}
