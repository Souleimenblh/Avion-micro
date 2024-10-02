package com.souleimen.avions.service;

//import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

//import com.souleimen.avions.dto.Avion;
import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.entities.TypeAv;
import com.souleimen.avions.repos.AvionRepository;
import com.souleimen.avions.repos.TypeAvRepository;

@Service

public class AvionServiceImpl implements AvionService {
	
	
	@Autowired
	ModelMapper modelMapper;

	
	
	@Autowired
	AvionRepository avionRepository;
	
	@Autowired
	TypeAvRepository typeAvRepository;

	@PreAuthorize("hasAuthority('Admin')")
	@Override
	public Avion saveAvion(Avion a) {
		return convertEntityToDto( avionRepository.save(convertDtoToEntity(a)));
	}

	@Override
	public Avion updateAvion(Avion a) {
		return convertEntityToDto(avionRepository.save(convertDtoToEntity(a)));

	}

	@Override
	public void deleteAvion(Avion a) {
		avionRepository.delete(a);
	}

	@Override
	public void deleteAvionById(Long id) {
		avionRepository.deleteById(id);
		
	}

	@Override
	public Avion getAvion(Long id) {
		return convertEntityToDto( avionRepository.findById(id).get());

	}

	@Override
	public List<Avion> getAllAvions() {
		return avionRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

	}
	
	//OU BIEN
	/*
	List<Avion> avios = avionRepository.findAll();
	List<Avion> listprodDto = new ArrayList<>(avios.size());
	for (Avion a : avios)
	listprodDto.add(convertEntityToDto(a));
	return listprodDto;
	*/

	@Override
	public Page<Avion> getAllAvionsParPage(int page, int size) {
		return avionRepository.findAll(PageRequest.of(page, size));

	}

	@Override
	public List<Avion> findByMatriculeAvion(String matriculeAvion) {
		
		return avionRepository.findByMatriculeAvion(matriculeAvion);
	}

	@Override
	public List<Avion> findByMatriculeAvionContains(String matriculeAvion) {

		return avionRepository.findByMatriculeAvionContains(matriculeAvion);
	}

	
	  @Override public List<Avion> findByfindByMatriculeCelometrage(String
	  matriculeAvion, int celometrageAvion) { return
	  avionRepository.findByMatriculeCelometrage(matriculeAvion, celometrageAvion);
	  }
	  
	  @Override public List<Avion> findByTypeAv(TypeAv typeAv) { return
	  avionRepository.findByTypeAv(typeAv); }
	  
	  @Override public List<Avion> findByTypeAvIdA(Long id) {
	  
	  return avionRepository.findByTypeAvIdAv(id); }
	  
	  @Override public List<Avion> findByOrderByMatriculeAvAsc() {
	  
	  return avionRepository.findByOrderByMatriculeAvionAsc(); }
	  
	  @Override public List<Avion> trierAvionsMatriculeCelometrage() { return
	  avionRepository.trierAvionsMatriculeCelometrage(); }

	@Override
	public List<TypeAv> getAllTypeAvs() {
		return typeAvRepository.findAll();

	}

	@Override
	public Avion convertEntityToDto(Avion avion) {
		
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		
		
		Avion avionDTO = modelMapper.map(avion, Avion.class);
		 return avionDTO; 
		 }	
		
		
		/*
		Avion avionDTO = new Avion();
		
		avionDTO.setIdAvion(a.getIdAvion());
		avionDTO.setMatriculeAvion(a.getMatriculeAvion());
		avionDTO.setCelometrageAvion(a.getCelometrageAvion());
		avionDTO.setTypeAv(a.getTypeAv());
		
		return avionDTO;
		*/
		
		/*
		return Avion.builder()
				.idAvion(a.getIdAvion())
				.matriculeAvion(a.getMatriculeAvion())
				.celometrageAvion(a.getCelometrageAvion())
				.dateFabrication(a.getDateFabrication())
				.typeAv(a.getTypeAv())
				//.matriculeAv(a.getTypeAv().getMatriculeAvion)
				.build();
				*/
	
	@Override
	public Avion convertDtoToEntity(Avion avionDto) {
		
		Avion avion = new Avion();
		avion = modelMapper.map(avionDto, Avion.class);
		return avion;

		/*
		Avion avion = new Avion();
		avion.setIdAvion(avionDto.getIdAvion());
		avion.setMatriculeAvion(avionDto.getMatriculeAvion());
		avion.setCelometrageAvion(avionDto.getCelometrageAvion());
		avion.setDateFabrication(avionDto.getDateFabrication());
		avion.setTypeAv(avionDto.getTypeAv());


		
		return avion;
		*/
	}

	
}
