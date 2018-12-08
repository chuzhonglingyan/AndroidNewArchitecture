package ${basePackageName}.repository;

import ${basePackageName}.net.service.${modelNameUpperCamel}Service;
import ${basePackageName}.contract.${modelNameUpperCamel}Contract;
import com.yuntian.baselibs.lifecycle.BaseRepository;

/**
 * @author   ${author}
 * @time     ${date}
 * @describe ${describe}
 */
public class ${modelNameUpperCamel}Repository extends BaseRepository<${modelNameUpperCamel}Service> implements ${modelNameUpperCamel}Contract {



    public ${modelNameUpperCamel}Repository(${modelNameUpperCamel}Service service) {
        super(service);
    }









}


