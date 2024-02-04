package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.RequiredMaterial;
import himedia.project.highfourm.entity.pk.RequiredMaterialPK;

public interface RequiredMaterialRepository extends JpaRepository<RequiredMaterial, RequiredMaterialPK>{

}
