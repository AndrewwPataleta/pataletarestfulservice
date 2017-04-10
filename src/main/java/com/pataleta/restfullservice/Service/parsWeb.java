package com.pataleta.restfullservice.Service;



import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface parsWeb {

     HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException;

     HashSet<SparepartEntity> getListByArticle(String article) throws IOException ;

     HashSet<SparepartEntity> getListByArticle(String article, String brand) throws IOException ;

     Set<String> getListOfBrands(String article);

     HashSet<SparepartEntity> getListByCode(String code) throws IOException;

     HashSet<SparepartEntity> getListByArticleWithAnalogs(String article) throws IOException;

     HashSet<SparepartEntity> getListByArticleWithAnalogs(String article, String brand) throws IOException;
}
