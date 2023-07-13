package com.artlogic.endoderapp.model
/*
* Name: Adebayo Osipitan
* Language: Kotlin
* Time Take: 2.5hrs
*/

class Encoder {

    fun encodeStringToIntAnswer(input: String): Int {
        val cArray = input.toCharArray()
        val binaryArr = charArrToBinaryArr(cArray)
        val encodedBinaryStr = binaryArr.let { encodeToBinaryString(it) }
        return encodedBinaryStr.let { encodeToInt(it) }
    }

    //decode Int back to  original string
    fun decodeEncryptedInt(input: Int): String {
        val binaryString = intToBinary(input, 32)
        return decode(binaryString).reversed()
    }

    private fun intToBinary(dec: Int, len: Int = 8): String {
        return String.format("%${len}s", Integer.toBinaryString(dec))
            .replace(' ', '0')
    }

    private fun charArrToBinaryArr(charArray: CharArray): Array<String> {
        val charArraySize = charArray.size
        val arr = ArrayList<String>()

        charArray.forEach {
            arr.add(intToBinary(it.code))
        }

        if (arr.size < 4) {
            val diff = 4 - charArraySize
            for (x in 1..diff) {
                arr.add(intToBinary(0))
            }
        }
        return arr.toTypedArray()

    }

    private fun encodeToBinaryString(binary: Array<String>): String {
        binary.reverse()
        val sb = StringBuilder()
        val binSize = 8
        var indx = 0

        for (index in 0 until binary[0].length) {
            for (i in 0 until binary.size) {
                sb.append("${binary[i][index]}")
                //sb.append("${binary[0][index]}${binary[1][index]}${binary[2][index]}${binary[3][index]}")
            }
        }
        return sb.toString()
    }

    private fun encodeToInt(binary: String): Int {
        return binary.toInt(2)
    }

    private fun decode(binaryString: String): String {
        var start = 0
        val sb0 = StringBuilder()
        val sb1 = StringBuilder()
        val sb2 = StringBuilder()
        val sb3 = StringBuilder()
        while (start < binaryString.length) {
            sb0.append(binaryString[start])
            sb1.append(binaryString[start + 1])
            sb2.append(binaryString[start + 2])
            sb3.append(binaryString[start + 3])
            start += 4
        }
        val toString = StringBuilder()
        toString.append(binaryStrToChar(sb0.toString()) ?: "")
        toString.append(binaryStrToChar(sb1.toString()) ?: "")
        toString.append(binaryStrToChar(sb2.toString()) ?: "")
        toString.append(binaryStrToChar(sb3.toString()) ?: "")
        return toString.toString()
    }

    private fun binaryStrToChar(binaryString: String): Char? {
        val dec = binaryString.toIntOrNull(2)
        return dec?.toChar()
    }
}