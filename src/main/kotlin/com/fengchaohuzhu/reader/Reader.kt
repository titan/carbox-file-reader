package com.fengchaohuzhu.box.Parser

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;


public data class Index(val filename: String? = null, val checkSum: String, val size: Int, var url: String? = null)
fun notNull(str: String) =str !=""

public fun main(args: Array<String>){
	println("Reader")
	val filename = """/home/luofei/Works/text.txt"""
	val res = getIndexByPath(filename)
	println(res)
}


public fun getIndexByPath(path: String): MutableList<Index> {
	val file = File(path)
	val contents = file.readText()
	val list: MutableList<Index> = getIndexByContent(contents)
	return list
}

public fun getIndexByContent(content: String): MutableList<Index>{
	val	strarr = content.split("\n")
	val new_strarr = strarr.filter(::notNull)
	var list = mutableListOf<Index>()
	for(i in 0..new_strarr.size-1) {
		if(i == 0) {
			var strarray: List<String> = new_strarr.get(i).split("|")
			val index = Index(filename = strarray.get(0), checkSum = strarray.get(1), size = (strarray.get(2)).toInt())
			list.add(index)
		}else {
			var strarray: List<String> = new_strarr.get(i).split("|")
			val index = Index(url = strarray.get(0), checkSum = strarray.get(1), size = (strarray.get(2)).toInt())
			list.add(index)
		}
	}
	return list
}