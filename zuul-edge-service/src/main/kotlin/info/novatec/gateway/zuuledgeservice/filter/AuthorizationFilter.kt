package info.novatec.gateway.zuuledgeservice.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils

@Component
class AuthorizationFilter : ZuulFilter() {
    override fun run(): Any? {
        val ctx = RequestContext.getCurrentContext()
        val customerId = WebUtils.getCookie(ctx.request, "customer-Id")?.value ?: ""

        if (customerId.isNotEmpty()) {
            ctx.addZuulRequestHeader(AUTHORIZATION, customerId)
            return null
        } else {
            ctx.responseStatusCode = BAD_REQUEST.value()
            ctx.setSendZuulResponse(false)
            throw ZuulRuntimeException(
                    ZuulException("Cookie 'customer-Id' missing.", BAD_REQUEST.value(), null)
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