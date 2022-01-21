package us.hyalen.springtemplate.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.core.Domain;
import us.hyalen.springtemplate.core.dao.CompanyDao;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.mapper.CompanyMapper;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl extends Domain implements CompanyService {
    @Autowired
    CompanyDao dao;

    @Override
    public List<CompanyModel> getAllCompanies() {
        return (List<CompanyModel>) dao.findAll();
    }

    @Override
    public Optional<CompanyDto> findByName(String name) {
        var model = dao.findByName(name);

        return Optional.ofNullable(model.isEmpty() ? null : CompanyMapper.INSTANCE.mapModelToDto(model.get()));
    }

    @Override
    public void update(CompanyDto dto) {
        validate();
        dao.update(CompanyMapper.INSTANCE.mapDtoToModel(dto));
    }
}