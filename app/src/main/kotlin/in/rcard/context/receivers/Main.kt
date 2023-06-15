/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package `in`.rcard.context.receivers

import `in`.rcard.context.receivers.domain.JOBS_DATABASE
import `in`.rcard.context.receivers.domain.Job
import `in`.rcard.context.receivers.domain.JobId
import java.util.logging.Level

fun main() {
    with(jobJsonScope) {
        println(printAsJson(JOBS_DATABASE.values.toList()))
    }
}

class JobController(private val jobs: Jobs) {

    context (JsonScope<Job>, Logger)
    suspend fun findJobById(id: String): String {
        log(Level.INFO, "Searching job with id $id")
        val jobId = JobId(id.toLong())
        return jobs.findById(jobId)?.let {
            log(Level.INFO, "Job with id $id found")
            return it.toJson()
        } ?: "No job found with id $id"
    }
}

fun printAsJson(objs: List<Job>) =
    objs.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toJson() }

fun Job.toJson(): String =
    """
        {
            "id": ${id.value},
            "company": "${company.name}",
            "role": "${role.name}",
            "salary": $salary.value}
        }
    """.trimIndent()

fun <T> JsonScope<T>.printAsJson(objs: List<T>) =
    objs.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toJson() }

interface Jobs {
    suspend fun findById(id: JobId): Job?
}

class LiveJobs : Jobs {
    override suspend fun findById(id: JobId): Job? = JOBS_DATABASE[id]
}

interface JsonScope<T> {
    fun T.toJson(): String
}

val jobJsonScope = object : JsonScope<Job> {
    override fun Job.toJson(): String {
        return """
            {
                "id": ${id.value},
                "company": "${company.name}",
                "role": "${role.name}",
                "salary": $salary.value}
            }
        """.trimIndent()
    }
}

interface Logger {
    fun log(level: Level, message: String)
}

val consoleLogger = object : Logger {
    override fun log(level: Level, message: String) {
        println("[$level] $message")
    }
}
