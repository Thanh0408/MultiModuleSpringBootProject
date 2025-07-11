package demo.com.vn.converter;


import demo.com.vn.utils.EnumStatus;
import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RentalStatusConverter implements AttributeConverter<EnumStatus, String> {

    @Override
    public String convertToDatabaseColumn(EnumStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public EnumStatus convertToEntityAttribute(String code) {
        return StringUtils.isNotBlank(code) ? EnumStatus.fromCode(code) : null;
    }
}

