package demo.pretty_json

import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.InputStream
import java.math.BigDecimal
import java.math.BigInteger

class PrettyJsonMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PrettyJsonMain().stuff()
        }
    }

    private fun stuff() {
        val dto = DTO("this is a", 1000, mapOf(Pair("Key1", "val1"), Pair("key2", "val2")), listOf("one","two","three","four"))



        val mapper = ObjectMapper().registerModule(KotlinModule())

        val pp = DefaultPrettyPrinter()
        pp.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE)
//        val generator = UTF8JsonGenerator()
        //pp.beforeArrayValues(MyGenerator())

//        val stream = ByteArrayOutputStream()
//        val generator = JsonFactory().createGenerator(stream)
//        generator.

        println(mapper.writer(pp).writeValueAsString(dto))

        val result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto)//.replace(",", ",\n")

        println(result)
    }
}

class MyGenerator : JsonGenerator() {
    override fun version(): Version? {
        throw UnsupportedOperationException()
    }

    override fun writeRawUTF8String(text: ByteArray?, offset: Int, length: Int) {
        throw UnsupportedOperationException()
    }

    override fun getOutputContext(): JsonStreamContext? {
        throw UnsupportedOperationException()
    }

    override fun setFeatureMask(values: Int): JsonGenerator? {
        throw UnsupportedOperationException()
    }

    override fun writeEndArray() {
        throw UnsupportedOperationException()
    }

    override fun writeNull() {
        throw UnsupportedOperationException()
    }

    override fun isClosed(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun close() {
        throw UnsupportedOperationException()
    }

    override fun writeStartArray() {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: Long) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: BigInteger?) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: Double) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: Float) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(v: BigDecimal?) {
        throw UnsupportedOperationException()
    }

    override fun writeNumber(encodedValue: String?) {
        throw UnsupportedOperationException()
    }

    override fun writeStartObject() {
        throw UnsupportedOperationException()
    }

    override fun setCodec(oc: ObjectCodec?): JsonGenerator? {
        throw UnsupportedOperationException()
    }

    override fun writeEndObject() {
        throw UnsupportedOperationException()
    }

    override fun writeRawValue(text: String?) {
        throw UnsupportedOperationException()
    }

    override fun writeRawValue(text: String?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeRawValue(text: CharArray?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun isEnabled(f: Feature?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun disable(f: Feature?): JsonGenerator? {
        throw UnsupportedOperationException()
    }

    override fun writeString(text: String?) {
        throw UnsupportedOperationException()
    }

    override fun writeString(text: CharArray?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeString(text: SerializableString?) {
        throw UnsupportedOperationException()
    }

    override fun useDefaultPrettyPrinter(): JsonGenerator? {
        throw UnsupportedOperationException()
    }

    override fun getFeatureMask(): Int {
        throw UnsupportedOperationException()
    }

    override fun flush() {
        throw UnsupportedOperationException()
    }

    override fun writeFieldName(name: String?) {
        throw UnsupportedOperationException()
    }

    override fun writeFieldName(name: SerializableString?) {
        throw UnsupportedOperationException()
    }

    override fun enable(f: Feature?): JsonGenerator? {
        throw UnsupportedOperationException()
    }

    override fun writeBoolean(state: Boolean) {
        throw UnsupportedOperationException()
    }

    override fun writeBinary(bv: Base64Variant?, data: ByteArray?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeBinary(bv: Base64Variant?, data: InputStream?, dataLength: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun getCodec(): ObjectCodec? {
        throw UnsupportedOperationException()
    }

    override fun writeObject(pojo: Any?) {
        throw UnsupportedOperationException()
    }

    override fun writeTree(rootNode: TreeNode?) {
        throw UnsupportedOperationException()
    }

    override fun writeRaw(text: String?) {
        throw UnsupportedOperationException()
    }

    override fun writeRaw(text: String?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeRaw(text: CharArray?, offset: Int, len: Int) {
        throw UnsupportedOperationException()
    }

    override fun writeRaw(c: Char) {

    }

    override fun writeUTF8String(text: ByteArray?, offset: Int, length: Int) {
        throw UnsupportedOperationException()
    }

}

