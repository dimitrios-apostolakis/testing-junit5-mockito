package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.junitextensions.TimingExtension;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);

        verify(specialtyRepository, times(1)).delete(any(Speciality.class));
    }

    @Test
    void findById() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = service.findById(1L);
        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void findByIdBdd() {
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
        Speciality foundSpeciality = service.findById(1L);
        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);
        
        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(2)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}