package com.example.demo.controller;

import com.example.demo.mapper.RegionMapper;
import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/region")
public class RegionController {

  private final RegionMapper regionMapper;

  @Autowired
  public RegionController(RegionMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  @GetMapping
  public List<Region> findAll() {
    return regionMapper.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Region> findById(@PathVariable Integer id) {
    return Optional.ofNullable(regionMapper.findById(id))
        .map(region -> new ResponseEntity<>(region, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Region region) {
    regionMapper.insert(region);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Region region) {
    return new ResponseEntity<>(
        Optional.ofNullable(regionMapper.findById(id))
            .map(
                it -> {
                  region.setId(id);
                  regionMapper.update(region);
                  return HttpStatus.NO_CONTENT;
                })
            .orElse(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    return new ResponseEntity<>(
        Optional.ofNullable(regionMapper.findById(id))
            .map(
                region -> {
                  regionMapper.deleteById(id);
                  return HttpStatus.NO_CONTENT;
                })
            .orElse(HttpStatus.NOT_FOUND));
  }
}
