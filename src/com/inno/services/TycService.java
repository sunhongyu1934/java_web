package com.inno.services;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface TycService {
    public void Exec(List<String[]> list, String zhua);
    public List<String> ReadXls(InputStream in);
    public List<String> ReadXlsx(InputStream in);
    public List<String[]> Readpro(InputStream in);
}
