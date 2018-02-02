package info.novatec.gateway.zuuledgeservice.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils

@Component
class AuthorizationFilter : ZuulFilter() {
    override fun run(): Any? {
        val ctx = RequestContext.getCurrentContext()
        val token = WebUtils.getCookie(ctx.request, "customer-Id")?.value ?: ""

        if (token.isNotEmpty()) {
            ctx.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, token)
            return null
        } else {
            ctx.responseStatusCode = HttpStatus.BAD_REQUEST.value()
            ctx.setSendZuulResponse(false)
            throw ZuulRuntimeException(
                    ZuulException("Cookie 'customer-Id' missing.", HttpStatus.BAD_REQUEST.value(), null)
            )
        }
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return FilterConstants.PRE_TYPE
    }

    override fun filterOrder(): Int {
        return 0
    }
}