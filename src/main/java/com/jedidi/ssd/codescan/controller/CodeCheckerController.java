package com.jedidi.ssd.codescan.controller;


/*
 * Code Made by Faouzi Jedidi
 * S1719017
 *
 */


import com.jedidi.ssd.codescan.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
public class CodeCheckerController {
    public String codeInFile;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView check(@RequestParam("code") String code) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        List<String> vulnReport = report(code);
        String reports = "";
        if (vulnReport.isEmpty()) {
            modelAndView.addObject("report", "No vulnerabilities found!");
        } else {
            modelAndView.addObject("report", vulnReport);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("file") final MultipartFile file, final ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);
        InputStream inputStream = file.getInputStream();
        String result = IOUtils.toString(inputStream);
        codeInFile = result;
        System.out.print(result);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        modelAndView.addObject("message", result);
        return modelAndView;
    }
    public HashMap<String, String> vulnerabilityMap() {

        HashMap<String, String> vulnerabilities = new HashMap<String, String>();

        vulnerabilities.put("private volatile int[] arr = new int[20]", "Use private final AtomicIntegerArray atomicArray = new AtomicIntegerArray(20);instead.");
        vulnerabilities.put("createStatement", " prepareStatement are better than createStatement");
        vulnerabilities.put("sendRedirect", "Use of safeSendRedirect is better for security.");
        vulnerabilities.put("Math.Random", " Randomizer is more secure than Math.Random.");
        vulnerabilities.put("isSecure", "HTTPUtilities.isSecureChannel() is more Secure.");
        vulnerabilities.put("someFile.delete();", "Use if (!someFile.delete()) is recommended.");
        vulnerabilities.put("Class clazz = Class.forName(className);", "Classes Should not be loaded dynamically.");
        vulnerabilities.put("SHA1", "Use of SHA-256 is more Secure");
        vulnerabilities.put("NullCipher nc = new NullCipher();", "\"javax.crypto.NullCipher\" should not be used for anything other than testing.");
        vulnerabilities.put("@RequestMapping()", "RequestMapping Should specify HTTP Method");
        vulnerabilities.put("javax.crypto", "Use encrpytor rather than that");

        return vulnerabilities;
    }

//    public String vulnerabilityFileParse(){
//
//        return"";
//    }

    public List<String> report(String snippet) {

        List<String> report = new ArrayList<String>();
        HashMap<String, String> v = vulnerabilityMap();
        List<String> snippets = Arrays.asList(snippet.split("\\r?\\n"));
        List<String> items = new ArrayList<>();
        int lineNumber = 1;
        for (String item : snippets) {
            items.add("Line " + lineNumber + " : " + item);
            lineNumber++;
        }

        for (String line : items) {
            for (String vulnerability : v.keySet()) {
                if (line.contains(vulnerability)) {
                    String value = v.get(vulnerability);
                    report.add("Vulnerability found at "+line +" Known vulnerability flagged:"+ vulnerability+"\n Recommendation: "+ value+"\n");
                }
            }
        }
        return report;

    }
}


