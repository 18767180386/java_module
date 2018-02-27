package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.Printer;

public interface PrinterMapper {

    public void add(Printer printer);
    
    public void update(Printer printer);
    
    public void deleteById(Long id);
    
    public Printer getById(Long id);
    
    public List<Printer> getPrinterByStoreId(String printer_storeid);
    

}
